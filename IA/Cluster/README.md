INTRUÇÕES DE USO

Entre na pasta: uniqueUserDetector/IA/Cluster

Crie um ambiente virtual: python -m venv .venv

Ative venv no linux/bash/powershell: source .venv/Scripts/activate

Ou ative venv no windows/cmd: .venv/Scripts/activate.bat

Instale as bibliotecas: pip install -r requirements.txt

Execute o código: python uniq_user_test.py

Na primeira vez o código vai perguntar se quer treinar a IA, então digite f pra prosseguir sem treinar a IA e pressione Enter.

Para validação de funcionalidade acesse o banco de dados através de uma ferramenta(DBeaver) e execute o comando sql abaixo: 
select count(usuario_id) as total,cluster_id from dedupe_cluster
group by cluster_id 
order by total desc 

Nele irá mostrar o total de usuários que contém em cada cluster.

