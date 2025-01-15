from django.urls import path
from .views import generate_pie_chart

urlpatterns = [
    path('chart/', generate_pie_chart, name='generate_pie_chart'),
]
