package com.examples.coding.brdemo2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val demoReceiver = DemoReceiver()
    val intentFilter = IntentFilter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intentFilter.addAction("com.examples.coding.broadcastreceiversandservicesdemo.demoreceiver")

    }
    override fun onStart() {
        super.onStart()
        registerReceiver(demoReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(demoReceiver)
    }

    inner class DemoReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val messageReceived = intent.getStringExtra("KEY")
            Log.d("TAG", "Message Received: $messageReceived")
            tvDisplay.text = messageReceived
        }
    }

    fun onClick(view: View) {
        val enteredString = etStringToBroadcast.text.toString()
        val intent = Intent("com.examples.coding.broadcastreceiversandservicesdemo.demoreceiver")
        intent.putExtra("KEY", enteredString)
        sendBroadcast(intent)
    }
}
