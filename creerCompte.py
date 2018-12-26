import unittest
import pytest
from base.basepage import BasePage

from utilities.util import Util
from pages.completerProfil.loginPage import LoginPage
from pages.completerProfil.homePage import HomePage
from pages.completerProfil.optionPage import OptionPage
from pages.completerProfil.identitePage import IdentitePage
from pages.completerProfil.conditionsUtilisationPage import Conditions
from pages.completerProfil.adressePage import AdressePage
from pages.completerProfil.typeRepresentationPage import TypeRepPage
from pages.otherPages.gmailPage import GmailPage





class CreerCompteGmail(BasePage):

    def __init__(self,driver):
        BasePage.__init__(self,driver)
        self.driver = driver



    def CreerCompte(self,user):

        self.hp = HomePage(self.driver)
        self.lp = LoginPage(self.driver)
        self.util = Util()
        self.gmailp = GmailPage(self.driver)


        self.util.eraseFile("automation.log")
        self.hp.clickDI()
        self.alias = self.util.getAlphaNumeric(6,type='mix')
        email = "AQMidi+"+user+self.alias+"@gmail.com"
        self.util.writeToFile("compte.txt",email)
        self.util.sleep(1)
        self.lp.demanderCodeDeVerification(email,"P@ssword123","P@ssword123")
        self.driver.execute_script("$(window.open('https://mail.google.com'))")
        windows = self.driver.window_handles
        self.driver.switch_to.window(windows[1])
        self.gmailp.gmailLogin("Aqmidi@gmail.com","Test$1234")
        codeVer = self.gmailp.recupererCode()
        self.driver.close()
        self.driver.switch_to.window(windows[0])

        self.lp.creerCompte(codeVer)
        self.util.sleep(2)
        return self.alias




