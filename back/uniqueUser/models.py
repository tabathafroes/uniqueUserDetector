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
    nome = models.CharField(max_length=40)
    email = models.CharField(max_length=50)
    telefone = models.BigIntegerField()
    senha = models.CharField(max_length=20)
    ip = models.CharField(max_length=50, blank=True, null=True)
    serial_number = models.CharField(max_length=50, blank=True, null=True)

    class Meta:
        db_table = 'usuario'


# class Ip(models.Model):
#     ip_id = models.BigAutoField(primary_key=True)
#     ip = models.BigIntegerField(blank=True, null=True)
#     usuario_id = models.ForeignKey(Usuario, models.DO_NOTHING)

#     class Meta:
#         db_table = 'ip'


# class SerialNumber(models.Model):
#     serial_number_id = models.BigAutoField(primary_key=True)
#     serial_number = models.CharField(max_length=50, blank=True, null=True)
#     usuario_id = models.ForeignKey(Usuario, models.DO_NOTHING)

#     class Meta:
#         db_table = 'serial_number'


# class UsuarioUnico(models.Model):
#     usuario_unico_id = models.BigAutoField(primary_key=True)
#     usuario = models.ForeignKey(Usuario, models.DO_NOTHING)

#     class Meta:
#         db_table = 'usuario_unico'
