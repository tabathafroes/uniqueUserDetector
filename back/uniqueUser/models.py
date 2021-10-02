# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models

class Usuario(models.Model):
    usuario_id = models.BigAutoField(primary_key=True)
    nome = models.CharField(max_length=100)
    email = models.CharField(max_length=100)
    telefone = models.BigIntegerField()
    senha = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'usuario'

class DadosEntropicosUser(models.Model):
    id = models.BigAutoField(primary_key=True)
    usuario = models.ForeignKey('Usuario', models.DO_NOTHING)
    cookies_enabled = models.BooleanField()
    device_memory = models.BigIntegerField()
    hardware_concurrency = models.BigIntegerField()
    languages = models.CharField(max_length=20)
    local_storage = models.BooleanField()
    platform = models.CharField(max_length=20)
    session_storage = models.BooleanField()
    timezone = models.CharField(max_length=50)
    touch_support = models.BooleanField()
    vendor = models.CharField(max_length=50)
    vendor_flavors = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'dados_entropicos_user'
