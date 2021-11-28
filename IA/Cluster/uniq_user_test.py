import read_data as rd
from multiprocessing import Process

import dedupe
import os 
import csv
import read_db
import write_db


if __name__ == "__main__":
    read_db.get_dados_entropicos_user()

    input_file = "dados100-final.csv"
    training_file = 'csv_example_training.json'
    settings_file = 'csv_example_learned_settings'
    output_file = 'csv_example_output.csv'

    fields = [
            {'field': 'usuario_id', 'type': 'String'},
            {'field': 'cookies_enabled', 'type': 'String'},
            {'field': 'device_memory', 'type': 'String'},
            {'field': 'hardware_concurrency', 'type': 'String'},
            {'field': 'ip', 'type': 'String'},
            {'field': 'languages', 'type': 'String'},
            {'field': 'local_storage', 'type': 'String'},
            {'field': 'platform', 'type': 'String'},
            {'field': 'session_storage', 'type': 'String'},
            {'field': 'timezone', 'type': 'String'},
            {'field': 'touch_support', 'type': 'String'},
            {'field': 'browser', 'type': 'String'},
            {'field': 'browser_version', 'type': 'String'},
            {'field': 'gpu', 'type': 'String'},
            {'field': 'hash', 'type': 'String'}
        ]

    data_d = rd.readData(input_file)

    if os.path.exists(settings_file):
            print('reading from', settings_file)
            with open(settings_file, 'rb') as f:
                deduper = dedupe.StaticDedupe(f)

    deduper = dedupe.Dedupe(fields)

    if os.path.exists(training_file):
        print('reading labeled examples from ', training_file)
        with open(training_file, 'rb') as f:
            deduper.prepare_training(data_d, f)
    else:
        deduper.prepare_training(data_d)


    dedupe.console_label(deduper)

    deduper.train()

    def processData():
        global data_d, input_file, fields, training_file, settings_file, output_file
        with open(training_file, 'w') as tf:
                    deduper.write_training(tf)

        with open(settings_file, 'wb') as sf:
                    deduper.write_settings(sf)

        clustered_dupes = deduper.partition(data_d, 0.5)

        cluster_membership = {}
        for cluster_id, (records, scores) in enumerate(clustered_dupes):
            for record_id, score in zip(records, scores):
                cluster_membership[record_id] = {
                    "Cluster ID": cluster_id,
                    "confidence_score": score
                }
        clusters = []
        with open(output_file, 'w') as f_output, open(input_file) as f_input:
            write_db.drop_dedupe_cluster()
            write_db.create_dedupe_cluster()
            reader = csv.DictReader(f_input)
            fieldnames = ['Cluster ID', 'confidence_score'] + reader.fieldnames

            writer = csv.DictWriter(f_output, fieldnames=fieldnames)
            writer.writeheader()

            for row in reader:
                row_id = int(row['usuario_id'])
                row.update(cluster_membership[row_id])
                writer.writerow(row)
                clusters.append(row)
        write_db.write_dedupe_cluster(clusters)

    processData()