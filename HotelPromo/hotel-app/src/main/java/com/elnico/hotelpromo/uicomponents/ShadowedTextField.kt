@file:OptIn(ExperimentalMaterial3Api::class)

package com.elnico.hotelpromo.uicomponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.elnico.hotelpromo.ui.theme.GoldColor

@Composable
internal fun ShadowedTextField(
    modifier: Modifier, placeholder: String,
    textValue: MutableState<String>, isPassword: Boolean = false
) {

    var textFieldValue by remember {
        val value = TextFieldValue(textValue.value).copy(
            selection = TextRange(textValue.value.length)
        )
        mutableStateOf(value)
    }
    textFieldValue = textFieldValue.copy(
        text = textValue.value,
        selection = TextRange(textValue.value.length)
    )

    val focusManager = LocalFocusManager.current

    ConstraintLayout(modifier) {
        val (lower, upper) = createRefs()

        TextField(
            modifier = Modifier.constrainAs(lower) {
                start.linkTo(parent.start, 1.dp)
                end.linkTo(parent.end, -1.dp)
                top.linkTo(parent.top, 1.dp)
                bottom.linkTo(parent.bottom, -1.dp)
            }.fillMaxWidth().border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            ),
            value = textFieldValue,
            onValueChange = {},
            visualTransformation = if (!isPassword) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            placeholder = { Text(text = "") },
            //textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            singleLine = true
        )

        TextField(
            modifier = Modifier.constrainAs(upper) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }.fillMaxWidth().border(
                width = 1.dp,
                color = GoldColor,
                shape = RoundedCornerShape(10.dp)
            ),
            value = textFieldValue,
            visualTransformation = if (!isPassword) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = { newInput ->
                val newValue = newInput.text
                textValue.value = newValue

                textFieldValue = newInput.copy(
                    text = newValue
                )

                //creationViewModel.updateName(newValue)
                //saveIsActive.value = textFieldValue.text.isNotEmpty()
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = { Text(
                modifier = Modifier.fillMaxWidth(),
                text = placeholder,
                color = GoldColor
                //textAlign = TextAlign.Center,
                //style = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
            ) },
            //textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                },
                onGo = {},
                onNext = {},
                onPrevious ={},
                onSearch ={},
                onSend = {}
            )
        )
    }
}