from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *

import sys
import admin_login
import threading
import db
import main_window
import time

'''
@Author Kevin
@Date 08/15/2018

'''
class Login(QWidget):

    def __init__(self):
        super().__init__()

        self.initUI()

    def initUI(self):

        '''
        self.btn = QPushButton('Dialog', self)
        self.btn.move(20, 20)
        self.btn.clicked.connect(self.showDialog)

        self.le = QLineEdit(self)
        self.le.move(130, 22)

        self.setGeometry(300, 300, 290, 150)
        self.setWindowTitle('Input dialog')
        self.show()
        '''

        self.name_label = QLabel('Employee Number:', self)
        self.name_label.move(30, 30)
        self.name_input = QLineEdit(self)
        self.name_input.move(300, 30)
        self.pwd_label = QLabel('Password:', self)
        self.pwd_label.move(30, 80)
        self.pwd_input = QLineEdit(self)
        self.pwd_input.move(300, 80)
        self.pwd_input.setEchoMode(QLineEdit.Password)

        self.login_btn = QPushButton('Login', self)
        self.login_btn.move(30, 160)
        self.close_btn = QPushButton('Close', self)
        self.close_btn.move(300, 160)
        self.login_btn.clicked.connect(self.login_click)
        self.close_btn.clicked.connect(QCoreApplication.instance().quit)

        #self.admin_label = QLabel('Admin Login', self)
        #self.admin_label.move(80, 240)
        #self.admin_label.setOpenExternalLinks(True)
        #alogin = admin_login.AdminLogin()

        self.error = QLabel('', self)
        self.error.move(60, 300)


        self.admin_login_btn = QPushButton('Admin Login', self)
        self.admin_login_btn.move(30, 240)
        self.admin_login_btn.clicked.connect(self.click_admin)
        #self.admin_label.linkActivated.connect(self.click_admin)

        self.setWindowTitle("Coupon System Login")
        self.setGeometry(300, 300, 600, 300)
        self.show()

    def click_admin(self):
        print("clicked")
        self.hide()
        self.adminWin = admin_login.AdminLogin()
        self.adminWin.show()
        #adminWin.show()
        #t = threading.Thread(target=self.testdb(), args=())

    def login_click(self):
        self.enum = self.name_input.text()
        self.pwd = self.pwd_input.text()
        t = threading.Thread(target=self.login_check(), args=())

    def login_check(self):
        r = db.check_login(self.enum, self.pwd)
        if (r != 'no'):
            print("login success")
            self.error.setText("login success")
            self.hide()
            try:
                self.m = main_window.MainWindow()
                self.m.initUI(2, r)
                self.m.show()
            except Exception as e:
                print(e)
        else:
            print("login failed")
            self.error.setText("login failed")

    def testdb(self):
        #db.clear_data()
        #db.init_data()
        #r = db.check_admin('admin', '12345')
        print("")


if __name__ == '__main__':
    print(time.strftime('%m', time.localtime()))
    print()
    app = QApplication(sys.argv)
    ex = Login()
    sys.exit(app.exec_())