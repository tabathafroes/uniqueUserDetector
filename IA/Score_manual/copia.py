import openpyxl
import score as sc

import psycopg2
from psycopg2 import Error

user_score_list = []

# CONEXÃO DO BANCO DE DADOS
try:
    connection = psycopg2.connect(user="oewnkeasnyvwck",
                                  password="2762727477cfd1a93e0f06bfe49179de169f8d2b710e6ece3a7803ad329cca76",
                                  host="ec2-54-156-60-12.compute-1.amazonaws.com",
                                  port="5432",
                                  database="d7ugngj77js1pi")

    cursor = connection.cursor()
    connection.commit()

    select_query = """SELECT * from dados_entropicos_user"""
    cursor.execute(select_query)
    teste = cursor.fetchall()

#percorrer cada linha da pagina
    for row in teste:
    #extrai os valores baseado na coluna+linha
        name = str(row[1])
        email = str(row[2])
        device_memory = str(row[3])
        hardware_concurrency = str(row[4])
        ip = str(row[5])
        language = str(row[6])
        platform = str(row[8])
        timezone = str(row[10])
        browser = str(row[12])
        version_browser = str(row[13])
        gpu = str(row[14])
        hash_user = str(row[16])

        # Printar todos os IDS cadastrados no BD
        print('ID: ', str(row[0]))
        

    #atualiza a user_score_list baseado nos dados coletados
        user_score_list = sc.add_request_to_score(user_score_list, name, email,
                                              device_memory, hardware_concurrency,
                                              ip, language, platform, timezone,
                                              browser, version_browser, gpu, hash_user)
        
    print("Foram encontrados " + str(len(user_score_list)) + " usuário(s) diferentes:")    
    for user in user_score_list:
        print("Name: " + user.name + " | Score: %.2f " % (user.score))

        update_query = """ INSERT INTO score_usuario (score, usuario_id) VALUES (%s, %s) """
        cursor.execute(update_query, (user.score, user.name))

        connection.commit()
        print("Deu certo! Score atualizado no BANCO na tabela SCORE_USUARIO!")
    
except (Exception, Error) as error:
    print("Deu ruim ao conectar com o PostgreSQL! Veja o erro a seguir: ", error)
finally:
    if (connection):
        cursor.close()
        connection.close()
        print("PostgreSQL connection ending.")
