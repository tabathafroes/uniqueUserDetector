from django.shortcuts import render, HttpResponse
from .models import Usuario
from .serializers import UsuarioSerializer
from django.http import JsonResponse
from rest_framework.parsers import JSONParser
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status

@api_view(['GET', 'POST'])
def usuario_list(request):
    if request.method == 'GET':
        usuarios = Usuario.objects.all()
        serializer = UsuarioSerializer(usuarios, many=True)
        return Response(serializer.data)
    elif request.method == 'POST':
        serializer = UsuarioSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)

@api_view(['GET', 'PUT', 'DELETE'])
def usuario_details(request, pk):
    try:
        usuario = Usuario.objects.get(pk=pk)

    except Usuario.DoesNotExist:
        return Response(status=status.HTTP_400_BAD_REQUEST)
    
    if request.method == 'GET':
        serializer = UsuarioSerializer(usuario)
        return Response(serializer.data)
    
    elif request.method == 'PUT':
        serializer = UsuarioSerializer(usuario, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    
    elif request.method == 'DELETE':
        usuario.delete()
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)

