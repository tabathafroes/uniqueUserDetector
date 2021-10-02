from django.urls import path
from .views import UsuarioList

urlpatterns = [
    path('usuarios/', UsuarioList.as_view()),
#    path('usuarios/<int:pk>/', usuario_details),
]
