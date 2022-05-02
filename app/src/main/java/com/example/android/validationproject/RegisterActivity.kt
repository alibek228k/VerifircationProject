package com.example.android.validationproject

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*


class RegisterActivity : AppCompatActivity() {

    private var viewModel: RegisterViewModel ?= null
    private var firstNameEditText: TextInputLayout ?= null
    private var lastNameEditText: TextInputLayout ?= null
    private var middleNameEditText: TextInputLayout ?= null
    private var usernameEditText: TextInputLayout ?= null
    private var iinEditText: TextInputLayout ?= null
    private var birthdayEditText: TextInputLayout ?= null
    private var phoneNumberEditText: TextInputLayout ?= null
    private var passwordEditText: TextInputLayout ?= null
    private var passwordConfirmEditText: TextInputLayout ?= null
    private var button: MaterialButton ?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        middleNameEditText = findViewById(R.id.middleNameEditText)
        usernameEditText = findViewById(R.id.usernameEditText)
        iinEditText = findViewById(R.id.iinEditText)
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText)
        birthdayEditText = findViewById(R.id.birthdayEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        passwordConfirmEditText = findViewById(R.id.passwordConfirmEditText)
        button = findViewById(R.id.registerButton)
        setupRegisterButton()
        setupBirthdayInputText()


    }

    private fun isFirstNameValidated():Boolean{
        val firstName: String = firstNameEditText?.editText?.text.toString().trim()
        return if (firstName.isNullOrBlank()){
            firstNameEditText?.error = "Field First name  cannot be empty!"
            true
        }else{
            firstNameEditText?.error = null
            firstNameEditText?.isErrorEnabled = false
            false
        }
    }

    private fun isLastNameValidated():Boolean{
        val lastName: String = lastNameEditText?.editText?.text.toString().trim()
        return if (lastName.isNullOrBlank()){
            lastNameEditText?.error = "Field Last name  cannot be empty!"
            true
        }else {
            lastNameEditText?.error = null
            lastNameEditText?.isErrorEnabled = false
            false
        }
    }

    private fun isMiddleNameValidated():Boolean{
        val middleName: String = middleNameEditText?.editText?.text.toString().trim()
        return if (middleName.isNullOrBlank()){
            middleNameEditText?.error = "Field Middle name  cannot be empty!"
            true
        }else {
            middleNameEditText?.error = null
            middleNameEditText?.isErrorEnabled = false
            false
        }
    }

    private fun isUsernameValidated():Boolean{
        val regex: String = "^[a-z0-9_-]{3,15}$"
        val username: String = usernameEditText?.editText?.text.toString().trim()
        return if (username.isNullOrBlank()){
            usernameEditText?.error = "Field username cannot be empty!!"
            true
        }else if (!username.matches(regex.toRegex())){
            usernameEditText?.error = "Invalid format"
            true
        }else {
            usernameEditText?.error = null
            usernameEditText?.isErrorEnabled = false
            false
        }
    }

    private fun isIINValidated():Boolean{
        val iin: String = iinEditText?.editText?.text.toString().trim()
        return if (iin.isNullOrBlank()){
            iinEditText?.error = "Field IIN cannot be empty!!"
            println("что то не так")
            true
        }else if (iin.length > 12 || iin.length < 12){
            iinEditText?.error = "Invalid format"
            true
        }else {
            lastNameEditText?.error = null
            lastNameEditText?.isErrorEnabled = false
            false
        }
    }


    private fun isPhoneNumberValidated():Boolean{
        val regex: String = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$"
        val phoneNumber: String = phoneNumberEditText?.editText?.text.toString().trim()
        return if (phoneNumber.isNullOrBlank()){
            phoneNumberEditText?.error = "Field phone number cannot be empty!!"
            true
        }else if (!phoneNumber.matches(regex.toRegex())){
            phoneNumberEditText?.error = "Invalid format, password should contain: at least 8 symbols, 1 uppercase letter, 1 special symbol (!/\$%^)"
            true
        }else {
            phoneNumberEditText?.error = null
            phoneNumberEditText?.isErrorEnabled = false
            false
        }
    }

    private fun isBirthDayValidated():Boolean{
        val regex: String = "^\\d{2}.\\d{2}.\\d{4}\$"
        val birthday: String = birthdayEditText?.editText?.text.toString().trim()
        return if (birthday.isNullOrBlank()){
            birthdayEditText?.error = "Field birthday cannot be empty!!"
            true
        }else if (!birthday.matches(regex.toRegex())){
            birthdayEditText?.error = "Invalid format!"
            true
        }else{
            birthdayEditText?.error = null
            birthdayEditText?.isErrorEnabled = false
            false
        }
    }

    private fun isPasswordValidated1():Boolean{
        val regex: String = "^(?=.{10,}\$)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*\$"
        val password: String = passwordEditText?.editText?.text.toString().trim()
        return if (password.isNullOrBlank()){
            passwordEditText?.error = "Field password cannot be empty!!"
            true
        }else if (!password.matches(regex.toRegex())){
            passwordEditText?.error = "Invalid format!"
            true
        }else{
            passwordEditText?.error = null
            passwordEditText?.isErrorEnabled = false
            false
        }
    }

    private fun isPasswordValidated2():Boolean{
        val password: String = passwordEditText?.editText?.text.toString().trim()
        val confirmPassword: String = passwordConfirmEditText?.editText?.text.toString().trim()
        return if (confirmPassword.isNullOrBlank()){
            passwordConfirmEditText?.error = "Field password confirm cannot be empty!!"
            true
        }else if (password != confirmPassword){
            passwordConfirmEditText?.error = "Passwords should be same"
            true
        }else{
            passwordConfirmEditText?.error = null
            passwordConfirmEditText?.isErrorEnabled = false
            false
        }
    }
    private fun setupRegisterButton(){
        button?.setOnClickListener {
            if (!isFirstNameValidated() && !isLastNameValidated() && !isMiddleNameValidated() && !isUsernameValidated() && !isIINValidated() && !isBirthDayValidated() && !isPhoneNumberValidated() && !isPasswordValidated1() && !isPasswordValidated2()) {
                val intent = Intent(this@RegisterActivity, AnotherActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
    }
    private fun setupBirthdayInputText(){

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayofMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayofMonth)
            updateLabel(myCalendar)
        }


        birthdayEditText?.editText?.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }


    }

    private fun updateLabel(calendar: Calendar){
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        birthdayEditText?.editText?.setText(sdf.format(calendar.time))
    }
}