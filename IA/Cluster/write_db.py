import db_connection as db 

def drop_dedupe_cluster():
    write_con = db.get_write_con()
    with write_con:
        with write_con.cursor() as cur:
            cur.execute("drop table if exists dedupe_cluster")

def create_dedupe_cluster():
    write_con = db.get_write_con()
    with write_con:
        with write_con.cursor() as cur:
            cur.execute("create table dedupe_cluster (usuario_id integer, cluster_id integer)")

def write_dedupe_cluster(data):
    write_con = db.get_write_con()
    with write_con:
        with write_con.cursor() as cur:
            for x in data:
                cur.execute("""insert into dedupe_cluster (usuario_id, cluster_id)
                            values ({0},{1})""".format(x["usuario_id"], x["Cluster ID"]))
            