# INTRUÇÕES DE USO

Tenha Python 3.9 para cima.

Entre na pasta: uniqueUserDetector/IA/Cluster

Crie um ambiente virtual: 
```bash
python -m venv .venv
```
Ative venv no linux/bash/powershell: 
```bash
source .venv/Scripts/activate
```
Ou ative venv no windows/cmd: 
```bash
.venv/Scripts/activate.bat
```
Instale as bibliotecas: 
```bash
pip install -r requirements.txt
```
Execute o código: 
```bash
python uniq_user_test.py
```
Na primeira vez o código irá perguntar se deseja treinar a IA, então digite f e pressione Enter para prosseguir sem treinar a IA.

Para validação de funcionalidade acesse o banco de dados através de uma ferramenta(DBeaver) e execute o comando sql abaixo: 
```bash
select count(usuario_id) as total,cluster_id from dedupe_cluster
group by cluster_id 
order by total desc 
```
Nele irá mostrar o total de usuários que contém em cada cluster.

