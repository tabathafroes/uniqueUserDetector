import openpyxl
import score as sc

user_score_list = []
#abrir o arquivo excel
wookbook = openpyxl.load_workbook("dados.xlsx")

#abrir primeira pagina ativa
worksheet = wookbook.active

#percorrer cada linha da pagina
for i in range(2, worksheet.max_row-1):
    #extrai os valores baseado na coluna+linha
    name = str(worksheet["A" + str(i)].value)
    cookies = str(worksheet["B" + str(i)].value)
    device_memory = str(worksheet["C" + str(i)].value)
    hardware_concurrency = str(worksheet["D" + str(i)].value)
    ip = str(worksheet["E" + str(i)].value)
    language = str(worksheet["F" + str(i)].value)
    platform = str(worksheet["G" + str(i)].value)
    timezone = str(worksheet["H" + str(i)].value)
    browser = str(worksheet["I" + str(i)].value)
    version_browser = str(worksheet["J" + str(i)].value)
    gpu = str(worksheet["K" + str(i)].value)
    hash_user = str(worksheet["L" + str(i)].value)
    

    #atualiza a user_score_list baseado nos dados coletados
    user_score_list = sc.add_request_to_score(user_score_list, name, cookies,
                                              device_memory, hardware_concurrency,
                                              ip, language, platform, timezone,
                                              browser, version_browser, gpu, hash_user)

    
print("Foram encontrados " + str(len(user_score_list)) + " usuário(s) diferentes:")
for user in user_score_list:
    print("Name: " + user.name + " Score: %.2f " % (user.score))
        








