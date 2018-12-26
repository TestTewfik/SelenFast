
from base.basepage import BasePage
import logging
import utilities.custom_logger as cl

class GmailPage(BasePage):

    # SENDER = 'Microsoft pour le compte.'
    # log = cl.customLogger(logging.DEBUG)

    def __init__(self,driver):
        BasePage.__init__(self,driver)
        self.driver = driver



    # Locators
    _gmailEmail = "identifierId"
    _gmailNextBtn = "identifierNext"
    _gmailPassword = "password"
    _gmailPassNextBtn = "passwordNext"
    _boiteRecLink = "//span[@class='nU n1']/child::a"
    _link1 = "//div[@id=':2x']/descendant::span[position()=1]"
    # _tableMsg = "//*[@id=':34']"
    _tableMsg ="//span[@email='msonlineservicesteam@microsoftonline.com']"

    _elementMsg = "//span[substring(@id,string-length(@id) -string-length('BodyPlaceholder_UserVerificationEmailBodySentence2') +1) = 'BodyPlaceholder_UserVerificationEmailBodySentence2']"
    _poubelleIcon = "//*[@id=':5']/div[2]/div[1]/div/div[2]/div[3]"



    def enterGoogleEmail(self,email):
        self.sendKeys(email,self._gmailEmail)

    def clicNextBtn(self):
        self.elementClick(self._gmailNextBtn)

    def enterGooglePassword(self,password):
        self.sendKeys(password,self._gmailPassword,locatorType='name')

    def clicNextPassBtn(self):
        self.elementClick(self._gmailPassNextBtn)

    def clicBoiteRecLink(self):
        self.elementClick(self._boiteRecLink,locatorType='xpath')

    def clicSupprimerMsg(self):
        self.elementClick(self._poubelleIcon,locatorType='xpath')

    def clicMsgMicrosoft(self):
        self.elementClick(self._tableMsg,locatorType='xpath')

    def recupererCode(self):
        count = 0
        self.element = self.getElement(self._tableMsg,locatorType='xpath')
        while(count <= 5):
            if self.elementPresenceCheck(self._tableMsg,byType='xpath'):
                self.clicMsgMicrosoft()
                break
            # self.driver.execute_script("location.reload()")
            self.driver.refresh()
            self.util.sleep(4)
            count+=1


        # self.waitForElement(self._tableMsg,locatorType='xpath',timeout=10)

        # self.clicMsgMicrosoft()

        # self.waitForElement(self._tableMsg,locatorType='xpath',timeout=5)
        # m = self.getElementList(self._tableMsg,locatorType='xpath')
        # for a in m:
        #
        #     if self.SENDER in a.text:
        #         a.click()
        #         break

        self.waitForElement(self._elementMsg,locatorType='xpath',timeout=5)
        texte = self.getText(self._elementMsg,locatorType='xpath')





        # elem = self.driver.find_element_by_xpath("//span[substring(@id,string-length(@id) -string-length('BodyPlaceholder_UserVerificationEmailBodySentence2') +1) = 'BodyPlaceholder_UserVerificationEmailBodySentence2']")
        codeVerif = texte[17:]
        self.clicSupprimerMsg()
        return codeVerif

    def gmailLogin(self,email,password):
        self.enterGoogleEmail(email)
        self.clicNextBtn()
        self.enterGooglePassword(password)
        self.clicNextPassBtn()



