# INTRUÇÕES DE USO

Tenha Python 3.9 para cima.

Entre na pasta no terminal: uniqueUserDetector/IA/Cluster

Crie uma variável de ambiente para o usuário com o nome LIB_PYTHON e valor deverá ser o package de instalação do pip. 
Geralmente, por padrão ele fica em (troque nome_do_usuario por do usuário da sua máquina ): 
C:\Users\nome_do_usuario\AppData\Local\Programs\Python\Python39\Lib\site-packages

Reinicie a máquina ou o Windows Explorer para aplicar a variável de ambiente.

Instale as bibliotecas: 
```bash
pip install -r requirements.txt
```
Execute o código (este comando substitui um arquivo da biblioteca dedupe para nunca perguntar se quer testar): 
```bash
python setup_dedupe.py
```
Execute o código: 
```bash
python uniq_user_test.py
```

Para validação de funcionalidade acesse o banco de dados através de uma ferramenta(DBeaver) e execute o comando sql abaixo: 
```bash
select count(usuario_id) as total,cluster_id from dedupe_cluster
group by cluster_id 
order by total desc 
```
Nele irá mostrar o total de usuários que contém em cada cluster.

