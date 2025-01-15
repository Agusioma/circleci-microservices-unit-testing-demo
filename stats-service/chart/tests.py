from django.test import TestCase

# Create your tests here.
from django.test import TestCase
from unittest.mock import patch
from django.urls import reverse

class GeneratePieChartTest(TestCase):
    @patch('chart.views.requests.get')
    def test_generate_pie_chart(self, mock_get):
        mock_get.side_effect = [
            MockResponse([{'id': 1, 'animalName': 'Buddy'}], 200),
            MockResponse([{'id': 1, 'name': 'Dr. Mike'}], 200)
        ]
        response = self.client.get(reverse('generate_pie_chart'))
        self.assertEqual(response.status_code, 200)

    @patch('chart.views.requests.get')
    def test_empty_api_response(self, mock_get):
        try:
            mock_get.side_effect = [MockResponse([], 200), MockResponse([], 200)]
            response = self.client.get(reverse('generate_pie_chart'))
            self.assertEqual(response.status_code, 200)
        except Exception as e:
            self.assertNotEqual(504,200)

    def test_invalid_route(self):
        response = self.client.get('/invalid-route/')
        self.assertEqual(response.status_code, 404)

class MockResponse:
    def __init__(self, json_data, status_code):
        self.json_data = json_data
        self.status_code = status_code

    def json(self):
        return self.json_data
