class Person:
  def __init__(self, score, name, cookies,
                          device_memory, hardware_concurrency,
                          ip, language, platform, timezone,
                          browser, version_browser, gpu, hash_user):
    self.score = score
    self.name = name
    self.cookies = cookies
    self.device_memory = device_memory
    self.hardware_concurrency = hardware_concurrency
    self.ip = ip
    self.language = language
    self.platform = platform
    self.timezone = timezone
    self.browser = browser
    self.version_browser = version_browser
    self.gpu = gpu
    self.hash_user = hash_user
    


#-----------------------POINTS-----------------------#
    
pcookies = 0.0001
pdevice_memory = 0.001
phardware_concurrency = 0.001
pip = 0.05
planguage = 0.0005
pplatform = 0.0005
ptimezone = 0.0005
pbrowser = 0.001
pversion_browser = 0.002
pgpu = 0.004
phash_user = 0.10

points = [pdevice_memory, phardware_concurrency,
        pip, planguage, pplatform, ptimezone, pbrowser,
        pversion_browser, pgpu, phash_user ]
#----------------------------------------------------#

def update_score(user_score,points):
    
    user_score.score -= points
    if user_score.score < 0:
      user_score.score = 0

    return user_score



def add_request_to_score(user_score_list, name, cookies,
                          device_memory, hardware_concurrency,
                          ip, language, platform, timezone,
                          browser, version_browser, gpu, hash_user):

    global points
    found = False
    user_score_checking = None
    index = 0

    #cria usuário novo  
    user_score_checking = Person(1.0, name, cookies, device_memory,
                                 hardware_concurrency,ip, language,
                                 platform, timezone,browser, version_browser,
                                 gpu, hash_user )

    
    #---------------------------------------------------------------------------------
    for user_score in user_score_list:
        aux = 0
        list_data = [user_score.device_memory, user_score.hardware_concurrency,
                     user_score.ip, user_score.language,
                     user_score.platform, user_score.timezone,
                     user_score.browser, user_score.version_browser,
                     user_score.gpu, user_score.hash_user]
        user_data = [device_memory, hardware_concurrency,
                     ip, language, platform, timezone,
                     browser, version_browser, gpu, hash_user]
        #comparações para reduzir score
        ##if user_score.cookies == True:
        #  user_score_checking = update_score(user_score_checking, pcookies)

        
        for data in list_data:
          if data == user_data[aux]:
            user_score_checking = update_score(user_score_checking, points[aux])
            user_score_list[index] = update_score(user_score_list[index], points[aux] )
          aux += 1
         
        index += 1
        
    #-------------------------------------------------------------------------------------
   
    user_score_list.append(user_score_checking)
 

    return user_score_list
    
