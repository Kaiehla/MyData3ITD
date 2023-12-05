//SECOND ACTIVITY KAY SER

package com.example.mydata3itd

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class DatabaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
    }

    //SAVE BUTTON
    fun saveRecord(view: View){
        val u_id = findViewById<EditText>(R.id.etId)
        val u_name = findViewById<EditText>(R.id.etUsername)
        val u_email = findViewById<EditText>(R.id.etEmail)

        val id = u_id.text.toString()
        val name = u_name.text.toString()
        val email = u_email.text.toString()

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if(id.trim()!="" && name.trim()!="" && email.trim()!="") {
            val status = databaseHandler.addEmployee(EmpModelClass(Integer.parseInt(id), name, email))

            //if the value of the status is greater than -1 it means may nilalagay tayo sa loob
            if (status > -1) {
                Toast.makeText(this, "Record Saved", Toast.LENGTH_LONG).show()
                u_id.text.clear()
                u_name.text.clear()
                u_email.text.clear()
            }
        }
        else{
            Toast.makeText(this, "ID or Name or Email cannot be blank", Toast.LENGTH_LONG).show()
        }
    }

    //VIEW BUTTON
    fun viewRecord(view: View){
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        val emp: List<EmpModelClass> = databaseHandler.viewEmployee()

        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayName = Array<String>(emp.size){"null"}
        val empArrayEmail = Array<String>(emp.size){"null"}

        //maglloop para maccess laman ng table
        var index = 0

        //e is counter
        //emp is the array list
        for (e in emp){
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArrayEmail[index] = e.userEmail
            index++
        }

        //display na sa custom_list
        //custom_list para lumabas sa listview
        val listView = findViewById<ListView>(R.id.listView)
        val myListAdapter = MyListAdapter(this, empArrayId, empArrayName, empArrayEmail)

        //eto na yun pangdugtong
        listView.adapter = myListAdapter
    }

    //UPDATE BUTTON
    fun updateRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val editId = dialogView.findViewById<EditText>(R.id.updateId)
        val editName = dialogView.findViewById<EditText>(R.id.updateName)
        val editEmail = dialogView.findViewById<EditText>(R.id.updateEmail)

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")

        //positive button
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener{ _, _ ->
            //kung ano gagawin ng lambda once update
            val updateId = editId.text.toString()
            val updateName = editName.text.toString()
            val updateEmail = editEmail.text.toString()

            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if (updateId.trim()!="" && updateName.trim()!="" && updateEmail!=""){
                val status = databaseHandler.updateEmployee(EmpModelClass(Integer.parseInt(updateId), updateName, updateEmail))

                if (status > -1){
                    Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(this, "ID or Name or Email cannot be blank", Toast.LENGTH_LONG).show()
            }
        })

        //negative button
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener{dialog, which ->

        })
        val b = dialogBuilder.create()
        b.show()
    }

    //DELETE BUTTON
    fun deleteRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val delId = dialogView.findViewById<EditText>(R.id.deleteId)

        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter data below")

        //positive button
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener{ _, _ ->
            //kung ano gagawin ng lambda once update
            val deleteId = delId.text.toString()

            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if (deleteId.trim()!=""){
                val status = databaseHandler.deleteEmployee(EmpModelClass(Integer.parseInt(deleteId),"", ""))

                if (status > -1){
                    Toast.makeText(this, "Record Deleted", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(this, "ID or Name or Email cannot be blank", Toast.LENGTH_LONG).show()
            }
        })

        //negative button
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener{dialog, which ->

        })
        val b = dialogBuilder.create()
        b.show()
    }
}