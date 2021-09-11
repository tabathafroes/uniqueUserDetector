from uniqueUser.views import usuario_list
from django.urls import path
from .views import usuario_details, usuario_list

urlpatterns = [
    path('usuarios/', usuario_list),
    path('usuarios/<int:pk>/', usuario_details),
]
