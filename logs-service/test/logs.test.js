const request = require('supertest');
const { sequelize } = require('../config/db');
const Log = require('../models/log');
const app = require('../server');

// Mock Sequelize and Log model
jest.mock('../config/db', () => {
    const SequelizeMock = require('sequelize-mock');
    const sequelizeMock = new SequelizeMock();

    return {
        sequelize: sequelizeMock,
        connectDB: jest.fn(),
    };
});

jest.mock('../models/log', () => {
    const { sequelize } = require('../config/db');
    return sequelize.define('Log', {
        message: 'test message',
    });
});

describe('POST /api/logs', () => {
    afterEach(() => {
        jest.clearAllMocks();
    });

    test('should save a log and return 201 with the log object', async () => {
        // Mock Log.create
        jest.spyOn(Log, 'create').mockResolvedValueOnce({
            id: 1,
            message: 'Test log message',
            timestamp: new Date(),
        });

        const response = await request(app)
            .post('/api/logs')
            .send({ message: 'Test log message' });

        expect(response.status).toBe(201);
        expect(response.body).toEqual({
            success: true,
            log: {
                id: 1,
                message: 'Test log message',
                timestamp: expect.any(String),
            },
        });
        expect(Log.create).toHaveBeenCalledWith({ message: 'Test log message' });
    });

    test('should return 400 if message is missing', async () => {
        const response = await request(app).post('/api/logs').send({});
        expect(response.status).toBe(400);
        expect(response.body).toEqual({ error: 'Message is required' });
    });

    test('should return 500 if there is a server error', async () => {
        jest.spyOn(Log, 'create').mockRejectedValueOnce(new Error('Unknown database error'));

        const response = await request(app)
            .post('/api/logs')
            .send({ message: 'Test log message' });

        expect(response.status).toBe(500);
        expect(response.body).toEqual({ error: 'Failed to save log' });
    });
    afterEach(done => {
        jest.clearAllMocks();
        done();
    })
});
