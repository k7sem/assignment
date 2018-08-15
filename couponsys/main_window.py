from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *

import sys
import admin_login
import threading
import db
import time

'''
@Author Kevin
@Date 08/15/2018

'''
class MainWindow(QWidget):
    def __init__(self):
        super().__init__()

    def initUI(self, flag, values):
        # admin role
        if (flag == 1):
            self.team1_label = QLabel('Number Of Coupon (team1):', self)
            self.team1_label.move(30, 30)
            self.team1_label_num = QLabel('', self)
            self.team1_label_num.move(350, 30)

            self.team2_label = QLabel('Number Of Coupon (team2):', self)
            self.team2_label.move(30, 60)
            self.team2_label_num = QLabel('', self)
            self.team2_label_num.move(350, 60)

            self.get_all_coupon()

        if (flag == 2):
            self.name_label = QLabel('UserName:', self)
            self.name_label.move(30, 30)
            self.name_str_label = QLabel(values[0][1], self)
            self.name_str_label.move(150, 30)

            if (values[0][4] == 1):
                self.leader_str_label = QLabel('(Leader)', self)
                self.leader_str_label.move(250, 30)
            self.teamid_label = QLabel('team:', self)
            self.teamid_label.move(30, 60)
            self.teamid_str_label = QLabel(str(values[0][3]), self)
            self.teamid = values[0][3]
            self.teamid_str_label.move(150, 60)

            self.emnum_label = QLabel('Employee Number:', self)
            self.emnum_label.move(30, 90)
            self.emum_str_label = QLabel(str(values[0][5]), self)
            self.emum_str_label.move(250, 90)

            self.coupon_label = QLabel('Number Of Coupon:', self)
            self.coupon_label.move(30, 120)
            self.coupon_str_label = QLabel('0', self)
            self.coupon_str_label.move(250, 120)
            self.get_coupon_num()

        if (flag == 2 and values[0][4] == 1):
            day = time.strftime('%d', time.localtime())
            if (int(day) < 5):
                self.reqcoupon_btn = QPushButton('Request Coupon', self)
                self.reqcoupon_btn.move(30, 150)
                self.coupon_line = QLineEdit(self)
                self.coupon_line.move(230, 150)
                self.reqcoupon_btn.clicked.connect(self.update_coupon)
            else:
                self.info_label = QLabel('Coupon request limited due to exceed date', self)
                self.info_label.move(50, 200)

        self.setWindowTitle("Coupon System")
        self.setGeometry(300, 300, 600, 300)


    def get_coupon_num(self):
        t = threading.Thread(target=self.fill_data(), args=())

    def fill_data(self):
        r = db.get_coupon_number(self.teamid)
        print(r)
        self.coupon_str_label.setFixedWidth(200)
        self.coupon_str_label.setText(str(r[0][1]))

    def update_coupon(self):
        t1 = threading.Thread(target=self.update_coupon2db(), args=())

    def update_coupon2db(self):
        db.update_coupon(int(self.coupon_line.text()), self.teamid)
        self.coupon_line.setText("")
        self.get_coupon_num()

    def get_all_coupon(self):
        t = threading.Thread(target=self.get_all_coupon_indb(), args=())
    def get_all_coupon_indb(self):
        r1 = db.get_coupon_number(1)
        r2 = db.get_coupon_number(2)
        try:
            self.team1_label_num.setText(str(r1[0][1]))
            self.team2_label_num.setText(str(r2[0][1]))
        except Exception as e:
            print(e)


if __name__ == '__main__':
    m = MainWindow()
