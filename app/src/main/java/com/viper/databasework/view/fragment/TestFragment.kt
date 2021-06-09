package com.viper.databasework.view.fragment

import com.viper.databasework.R
import com.viper.databasework.base.BaseFragment
import com.viper.databasework.databinding.FragmentTestBinding
import com.viper.databasework.log
import com.viper.databasework.showToast
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class TestFragment : BaseFragment<FragmentTestBinding>() {
    override fun onInitView() {

    }

    override fun onInitAction() {
        binding.btnConnectTest.setOnClickListener {
            Thread {
                val sql = "SELECT * FROM test"
//                val sql = "SELECT * FROM dbwork.Student"
                mysqlConnection(sql)
            }.start()
        }
    }

    override fun getContentViewResId() = R.layout.fragment_test

    private fun mysqlConnection(sql: String) {
        "开始连接".log()
//        val cn: Connection
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver")
//            cn = DriverManager.getConnection(
//                "jdbc:mysql://192.168.43.196:3306/homework?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
//                "admin",
//                "000000"
//            )
////            cn = DriverManager.getConnection(
////                "jdbc:mysql://192.168.43.124:3306/",
////                "myuser",
////                "000000"
////            )
//            val ps = cn.createStatement()
//            ps.executeQuery(sql)?.let {
//                "连接成功".showToast()
//                while (it.next()) {
//                    (it.getInt("id").toString() + it.getString("name")).showToast()
//                }
//            }
//            ps?.close()
//            cn?.close()
//        } catch (e: ClassNotFoundException) {
//            e.printStackTrace()
//        } catch (e: SQLException) {
//            e.printStackTrace()
//        }
    }
}