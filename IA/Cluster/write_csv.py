import csv 

def get_file_writer():
    f = open('dados100-final.csv', 'w', encoding='UTF8')
    return f

def write(row, f):
    
    writer = csv.writer(f)
    writer.writerow(row)

