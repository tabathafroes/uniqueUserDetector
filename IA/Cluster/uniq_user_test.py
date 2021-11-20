import read_data as rd
from multiprocessing import Process

import dedupe
import os 
import csv

if __name__ == "__main__":
    input_file = "dados100-final.csv"
    training_file = 'csv_example_training.json'
    settings_file = 'csv_example_learned_settings'
    output_file = 'csv_example_output.csv'

    fields = [
            {'field': 'Id', 'type': 'String'},
            {'field': 'cookies', 'type': 'String'},
            {'field': 'Device Memory', 'type': 'String'},
            {'field': 'hardware concurrency', 'type': 'String'},
            {'field': 'IP', 'type': 'String'},
            {'field': 'Lingua', 'type': 'String'},
            {'field': 'Local_Storage', 'type': 'String'},
            {'field': 'Plataforma', 'type': 'String'},
            {'field': 'Session_Storage', 'type': 'String'},
            {'field': 'Timezone', 'type': 'String'},
            {'field': 'Touch_Support', 'type': 'String'},
            {'field': 'Navegador', 'type': 'String'},
            {'field': 'Versao Navegador', 'type': 'String'},
            {'field': 'GPU', 'type': 'String'},
            {'field': 'Hash', 'type': 'String'}
        ]

    data_d = rd.readData(input_file)

    if os.path.exists(settings_file):
            print('reading from', settings_file)
            with open(settings_file, 'rb') as f:
                deduper = dedupe.StaticDedupe(f)
    else:
        fields = [
            {'field': 'Id', 'type': 'String'},
            {'field': 'cookies', 'type': 'String'},
            {'field': 'Device Memory', 'type': 'String'},
            {'field': 'hardware concurrency', 'type': 'String'},
            {'field': 'IP', 'type': 'String'},
            {'field': 'Lingua', 'type': 'String'},
            {'field': 'Local_Storage', 'type': 'String'},
            {'field': 'Plataforma', 'type': 'String'},
            {'field': 'Session_Storage', 'type': 'String'},
            {'field': 'Timezone', 'type': 'String'},
            {'field': 'Touch_Support', 'type': 'String'},
            {'field': 'Navegador', 'type': 'String'},
            {'field': 'Versao Navegador', 'type': 'String'},
            {'field': 'GPU', 'type': 'String'},
            {'field': 'Hash', 'type': 'String'}
        ]

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

        with open(output_file, 'w') as f_output, open(input_file) as f_input:

            reader = csv.DictReader(f_input)
            fieldnames = ['Cluster ID', 'confidence_score'] + reader.fieldnames

            writer = csv.DictWriter(f_output, fieldnames=fieldnames)
            writer.writeheader()

            for row in reader:
                row_id = int(row['Id'])
                row.update(cluster_membership[row_id])
                writer.writerow(row)

    processData()
    # p1 = Process(target=processData)
    # p1.start()
    # p1.join()