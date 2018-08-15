from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *

import sys
import login
import threading
import db
import main_window

'''
@Author Kevin
@Date 08/15/2018

'''
class AdminLogin(QWidget):

    def __init__(self):
        super().__init__()

        self.initUI()

    def initUI(self):

        print("admin initUI")

        self.aname_label = QLabel('Admin name:', self)
        self.aname_label.move(30, 30)
        self.aname_input = QLineEdit(self)
        self.aname_input.move(300, 30)
        self.apwd_label = QLabel('Password:', self)
        self.apwd_label.move(30, 80)
        self.apwd_input = QLineEdit(self)
        self.apwd_input.move(300, 80)
        self.apwd_input.setEchoMode(QLineEdit.Password)

        self.alogin_btn = QPushButton('Login', self)
        self.alogin_btn.move(30, 160)
        self.aclose_btn = QPushButton('Close', self)
        self.aclose_btn.move(300, 160)
        self.alogin_btn.clicked.connect(self.login_click)
        self.aclose_btn.clicked.connect(QCoreApplication.instance().quit)

        self.error = QLabel('                 ', self)
        self.error.move(200, 240)
        self.setWindowTitle("Admin Login")
        self.setGeometry(300, 300, 600, 300)
       # self.show()

    def login_click(self):
        self.n = self.aname_input.text()
        self.p = self.apwd_input.text()
        t = threading.Thread(target=self.check_admin_login(), args=())

    def check_admin_login(self):
        r = db.check_admin(self.n, self.p)
        if (r == 'yes'):
            print("login success")
            self.error.setText("login success")
            self.hide()
            try:
                self.m = main_window.MainWindow()
                self.m.initUI(1, r)
                self.m.show()
            except Exception as e:
                print(e)
        else:
            print("login failed")
            self.error.setText("login failed")


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = AdminLogin()
    sys.exit(app.exec_())