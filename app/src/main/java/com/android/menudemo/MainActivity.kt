package com.android.menudemo

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.android.menudemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        popUpMenuTasks()
        contextMenuTasks()
    }

    private fun contextMenuTasks() {
        val dataSet = arrayOf(
            "Developer 1 : +1 323 323 53",
            "Developer 2 : +1 545 989 53",
            "Developer 3 : +1 888 323 53"
        )

        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, dataSet
        )
        binding.listOfDevelopers.adapter = arrayAdapter
        registerForContextMenu(binding.listOfDevelopers)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.call -> {
                makeToast("Call")
                true
            }

            R.id.message -> {
                makeToast("Message")
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    private fun popUpMenuTasks() {
        val popUpMenu = PopupMenu(
            this,
            binding.btnPopUpMenuAnchor
        )
        popUpMenu.menuInflater.inflate(
            R.menu.pop_up_menu,
            popUpMenu.menu
        )

        popUpMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.mango -> makeToast(getString(R.string.mango))
                R.id.kiwi -> makeToast(getString(R.string.kiwi))
                R.id.apple -> makeToast(getString(R.string.apple))
            }
            true
        }
        binding.btnPopUpMenuAnchor.setOnClickListener {
            popUpMenu.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                makeToast(getString(R.string.settings))
                true
            }

            R.id.share -> {
                makeToast(getString(R.string.share))
                true
            }

            R.id.logout -> {
                makeToast(getString(R.string.logout))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message,
            Toast.LENGTH_SHORT).show()
    }
}