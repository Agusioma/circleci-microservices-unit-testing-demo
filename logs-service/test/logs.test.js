
import {use, expect} from 'chai';
import chaiHttp from "chai-http";
import app from '../server.js'
const chai = use(chaiHttp)

describe('Logs API Tests', () => {
    it('should create a new log', async () => {
        const res = await chai.request.execute(app)
            .post('/api/logs')
            .send({ message: 'INFO: Test log' });

        expect(res.status).to.equal(201);
        expect(res.body.success).to.be.true;
    });

    it('should return 400 for missing message', async () => {
        const res = await chai.request.execute(app)
            .post('/api/logs')
            .send({});

        expect(res.status).to.equal(400);
        expect(res.body.error).to.equal('Message is required');
    });
});
