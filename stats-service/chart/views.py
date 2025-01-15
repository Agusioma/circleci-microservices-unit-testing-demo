from django.shortcuts import render

import requests
import matplotlib.pyplot as plt
from django.http import HttpResponse
from io import BytesIO

def generate_pie_chart(request):
    # Fetch data from Spring Boot APIs
    visits_url = "http://localhost:8080/visits"
    veterinarians_url = "http://localhost:8080/veterinarians"

    try:
        visits_response = requests.get(visits_url)
        veterinarians_response = requests.get(veterinarians_url)

        visits_count = len(visits_response.json())
        veterinarians_count = len(veterinarians_response.json())
    except requests.exceptions.RequestException as e:
        return HttpResponse(f"Error fetching data: {e}", status=500)

    # Data for the chart
    labels = ['Visits', 'Veterinarians']
    sizes = [visits_count, veterinarians_count]
    colors = ['#66b3ff', '#99ff99']

    # Create the pie chart
    plt.figure(figsize=(9, 6))
    plt.pie(sizes, labels=labels, colors=colors, autopct='%1.1f%%', startangle=90)
    plt.title("Veterinarians vs Visits")

    # Save the chart to an in-memory buffer
    buffer = BytesIO()
    plt.savefig(buffer, format='png')
    plt.close()
    buffer.seek(0)

    # Return the chart as a response
    return HttpResponse(buffer, content_type='image/png')
