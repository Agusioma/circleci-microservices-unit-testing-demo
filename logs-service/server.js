require('dotenv').config();
const express = require('express');
const bodyParser = require('body-parser');
const { connectDB } = require('./config/db');
const Log = require('./models/log');

const app = express();
app.use(bodyParser.json());

// Connect to MySQL and sync models
connectDB();
(async () => {
    await Log.sync(); // Ensure the logs table is created
})();

// Endpoint to store logs
app.post('/api/logs', async (req, res) => {
    const { message } = req.body;

    if (!message) {
        return res.status(400).json({ error: 'Message is required' });
    }

    try {
        const log = await Log.create({ message });
        res.status(201).json({ success: true, log });
    } catch (error) {
        console.error('Error saving log:', error.message);
        res.status(500).json({ error: 'Failed to save log' });
    }
});

// Start the server
const PORT = process.env.PORT || 5001;
app.listen(PORT, () => {
    console.log(`Logs Service running on port ${PORT}`);
});

module.exports = app;