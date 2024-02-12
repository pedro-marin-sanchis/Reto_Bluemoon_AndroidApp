package com.uguinformatica.bluemoon.androidapp.ui.components.CartComponents

import android.view.animation.Transformation
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.asLiveData
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.uguinformatica.bluemoon.androidapp.dataStore
import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.CartViewModel
import kotlinx.coroutines.flow.map

@Composable
fun CartProductItem(cartItem: CartItem, cartViewModel: CartViewModel) {

    val product = cartItem.product

    Column {
        Card(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(start = 10.dp, top = 10.dp)
                        .clip(RoundedCornerShape(30))
                )

                Spacer(modifier = Modifier.padding(horizontal = 25.dp))

                Text(
                    text = "${product.name}   x ${cartItem.quantity}",
                    fontSize = 25.sp
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth().size(width = 0.dp, height = 40.dp)
            ) {
                TextButton(
                    onClick = { cartViewModel.openModifyDialog(quantity = cartItem.quantity, productId = product.id); println("editar") },
                ) {
                    Text(text = "Modify Quantity")
                }
                IconButton(
                    onClick = { cartViewModel.openDeleteDialog(product.id) },
                ){
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
                }
            }

        }
    }
}


