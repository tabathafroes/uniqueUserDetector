import db_connection as db 
import write_csv as csv

def get_dados_entropicos_user():
    f = csv.get_file_writer()
    header = ["usuario_id", "cookies_enabled", "device_memory", "hardware_concurrency", "ip", "languages", "local_storage", "platform", "session_storage", "timezone", "touch_support", "browser", "browser_version", "gpu", "hash"]
    csv.write(header, f)
    read_con = db.get_con()
    with read_con.cursor('dados_entropicos_user') as cur:
        cur.execute("""SELECT usuario_id, cookies_enabled || '' as cookies_enabled, device_memory, hardware_concurrency, 
                   ip, languages, local_storage || '' as local_storage, platform, session_storage || '' as session_storage, timezone, 
                   touch_support || '' as touch_support, browser, browser_version, gpu, hash
                   from dados_entropicos_user""")

        for row in cur:
            field_list = []
            field_list.append(row[0])
            field_list.append(row[1])
            field_list.append(row[2])
            field_list.append(row[3])
            field_list.append(row[4])
            field_list.append(row[5])
            field_list.append(row[6])
            field_list.append(row[7])
            field_list.append(row[8])
            field_list.append(row[9])
            field_list.append(row[10])
            field_list.append(row[11])
            field_list.append(row[12])
            field_list.append(row[13])
            field_list.append(row[14])

            csv.write(field_list, f)

