package com.register.festapp.presentation

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.register.festapp.R
import com.register.festapp.databinding.ActivityMainBinding
import com.register.festapp.di.AppComponent
import com.register.festapp.domain.entities.UserData
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    private val component: AppComponent by lazy {
        (application as ShoppingApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.dbData.observe(this) {
            if(it) {
                val intent = Intent(this, HostActivity::class.java)
                startActivity(intent)
            }
        }
        setupClearButton(binding.etName, binding.ivClearName)
        setupClearButton(binding.etSurname, binding.ivClearSurname)
        setupClearButton(binding.etPhone, binding.ivClearPhone)
        binding.btnEnter.isActivated = false
        binding.btnEnter.setOnClickListener {
            if(updateButtonState()) {
                binding.btnEnter.isActivated = true
                val intent = Intent(this, HostActivity::class.java)
                startActivity(intent)
                val data = UserData(
                    name = binding.etName.text.toString(),
                    surname = binding.etSurname.text.toString(),
                    phoneNumber = binding.etPhone.text.toString()
                )
                viewModel.saveUserData(data)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.etName.setText("")
        binding.etSurname.setText("")
    }

    private fun updateButtonState(): Boolean {
        val input1: String = binding.etName.text.toString().trim()
        val input2: String = binding.etSurname.text.toString().trim()
        val input3: String = binding.etPhone.text.toString().trim()

        return input1.isNotEmpty() && input2.isNotEmpty() && input3.length >= 10
    }

    private fun setupClearButton(editText: EditText, clearButton: ImageView) {
        clearButton.visibility = View.INVISIBLE
        editText.isFocusable = true
        editText.isFocusableInTouchMode = true
        when(editText) {
            binding.etPhone -> {
                editText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable) {
                        clearButton.visibility = if (s.isNotEmpty()) View.VISIBLE else View.INVISIBLE
                    }

                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                })
                clearButton.setOnClickListener {
                    editText.text.clear()
                    it.visibility = View.INVISIBLE
                }
            }
            else -> {
                editText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable) {
                        if (!s.toString().matches(Regex("[а-яА-Я]*"))) {
                            editText.background = ColorDrawable(Color.RED)
                        } else {
                            editText.background = ColorDrawable(Color.TRANSPARENT)
                        }
                        clearButton.visibility = if (s.isNotEmpty()) View.VISIBLE else View.INVISIBLE
                    }

                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                })

                editText.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
                    if (!source.toString().matches(Regex("[а-яА-Я]*"))) {
                        ""
                    } else {
                        null
                    }
                })

                clearButton.setOnClickListener {
                    editText.text.clear()
                    it.visibility = View.INVISIBLE
                }
            }
        }
    }

}
