const { Sequelize } = require('sequelize');

// Load environment variables
require('dotenv').config();

const sequelize = new Sequelize(process.env.MYSQL_DB, process.env.MYSQL_USER, process.env.MYSQL_PASSWORD, {
    host: process.env.MYSQL_HOST,
    dialect: 'mysql',
});

const connectDB = async () => {
    try {
        await sequelize.authenticate();
        console.log('MySQL connected successfully!');
    } catch (error) {
        console.error('Unable to connect to MySQL:', error.message);
        process.exit(1);
    }
};

module.exports = { sequelize, connectDB };