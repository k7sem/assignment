import mysql.connector

'''
@Author Kevin
@Date 08/15/2018

'''
def check_login(n, p):
    print("check login")
    print(n)
    print(p)
    try:
        conn = mysql.connector.connect(user='root', password='Qwerty;*#06#', database='coupon')
        cursor = conn.cursor()
        cursor.execute('select * from user where employnum = %s and userpwd = %s', (n, p,))
        values = cursor.fetchall()
        cursor.close()
        conn.close()
    except Exception as e:
        print(e)


    if (values):
        print(values)
        return values
    else:
        return 'no'




def get_coupon_number(teamid):
    print("get coupon number")
    try:
        conn = mysql.connector.connect(user='root', password='Qwerty;*#06#', database='coupon')
        cursor = conn.cursor()
        cursor.execute('select * from coupon where teamid = %s', (teamid,))
        values = cursor.fetchall()
        cursor.close()
        conn.close()
    except Exception as e:
        print(e)

    #print(values)
    if (values):
        print(values)
        return values
    else:
        return 'no'

def update_coupon(num, teamid):
    print("update coupon")
    try:
        conn = mysql.connector.connect(user='root', password='Qwerty;*#06#', database='coupon')
        cursor = conn.cursor()
        cursor.execute('UPDATE coupon set number = %s WHERE teamid = %s', (num, teamid,))
        #values = cursor.fetchall()
        conn.commit()
        cursor.close()
        conn.close()
    except Exception as e:
        print(e)


def check_admin(n, p):
    try:
        conn = mysql.connector.connect(user='root', password='Qwerty;*#06#', database='coupon')
        cursor = conn.cursor()
        cursor.execute('select * from admin where name = %s and password = %s', (n, p,))
        values = cursor.fetchall()
        cursor.close()
        conn.close()
    except Exception as e:
        print(e)

    #print(values)
    if (values):
        print(values)
        return 'yes'
    else:
        return 'no'




def clear_data():
    try:
        conn = mysql.connector.connect(user='root', password='Qwerty;*#06#', database='coupon')
        cursor = conn.cursor()
        cursor.execute('DELETE FROM admin')
        cursor.execute('DELETE FROM user')
        cursor.execute('DELETE FROM coupon')
        conn.commit()
        cursor.close()
        conn.close()
    except Exception as e:
        print(e)

def init_data():
    print("================= writing admin data =================== ")
    try:
        conn = mysql.connector.connect(user='root', password='Qwerty;*#06#', database='coupon')
        cursor = conn.cursor()
        cursor.execute('insert into admin (id, name, password) values (%s, %s, %s)', ['1', 'admin', '1234'])

        print("================ writing user data ======================")
        cursor.execute('insert into user (id, username, userpwd, teamnum, isleader, employnum) '
                       'values (%s, %s, %s, %s, %s, %s)', ['1', 'Lucy', '1234', '1', '0', '1000'])

        cursor.execute('insert into user (id, username, userpwd, teamnum, isleader, employnum) '
                       'values (%s, %s, %s, %s, %s, %s)', ['2', 'Tracy', '1234', '1', '0', '1001'])

        cursor.execute('insert into user (id, username, userpwd, teamnum, isleader, employnum) '
                       'values (%s, %s, %s, %s, %s, %s)', ['3', 'Fred', '1234', '1', '0', '1002'])

        cursor.execute('insert into user (id, username, userpwd, teamnum, isleader, employnum) '
                       'values (%s, %s, %s, %s, %s, %s)', ['4', 'Andy', '1234', '1', '0', '1003'])

        cursor.execute('insert into user (id, username, userpwd, teamnum, isleader, employnum) '
                       'values (%s, %s, %s, %s, %s, %s)', ['5', 'Carl', '1234', '1', '0', '1004'])

        cursor.execute('insert into user (id, username, userpwd, teamnum, isleader, employnum) '
                       'values (%s, %s, %s, %s, %s, %s)', ['6', 'David', '1234', '1', '1', '1005'])

        cursor.execute('insert into user (id, username, userpwd, teamnum, isleader, employnum) '
                       'values (%s, %s, %s, %s, %s, %s)', ['7', 'Ivy', '1234', '2', '0', '1006'])

        cursor.execute('insert into user (id, username, userpwd, teamnum, isleader, employnum) '
                       'values (%s, %s, %s, %s, %s, %s)', ['8', 'Jacky', '1234', '2', '0', '1007'])

        cursor.execute('insert into user (id, username, userpwd, teamnum, isleader, employnum) '
                       'values (%s, %s, %s, %s, %s, %s)', ['9', 'Jerry', '1234', '2', '0', '1008'])

        cursor.execute('insert into user (id, username, userpwd, teamnum, isleader, employnum) '
                       'values (%s, %s, %s, %s, %s, %s)', ['10', 'John', '1234', '2', '0', '1009'])

        cursor.execute('insert into user (id, username, userpwd, teamnum, isleader, employnum) '
                       'values (%s, %s, %s, %s, %s, %s)', ['11', 'Mark', '1234', '2', '0', '1010'])

        cursor.execute('insert into user (id, username, userpwd, teamnum, isleader, employnum) '
                       'values (%s, %s, %s, %s, %s, %s)', ['12', 'Sam', '1234', '2', '1', '1011'])

        print("================ writing coupon data ======================")
        cursor.execute('insert into coupon (id, number, month, teamid) '
                       'values (%s, %s, %s, %s)', ['1', '5', '8', '1'])

        cursor.execute('insert into coupon (id, number, month, teamid) '
                       'values (%s, %s, %s, %s)', ['2', '8', '8', '2'])



        #cursor.rowcount
        conn.commit()
        cursor.close()
        conn.close()
        print("writing data finished")
    except Exception as e:
        print(e)

