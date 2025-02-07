package com.example.recipes.fragments.addRecipes

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.recipes.R
import com.example.recipes.databinding.FragmentAddRecipesBinding
import com.example.recipes.room.AppDatabase
import com.example.recipes.room.RecipeDao
import com.example.recipes.room.RecipeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddRecipesFragment : Fragment() {
    private lateinit var binding: FragmentAddRecipesBinding

    /*private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>*/
    private var selectedImageUri: Uri? = null
    private lateinit var recipeDao: RecipeDao


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddRecipesBinding.inflate(inflater, container, false)
        binding.imageViewBackArrow.setOnClickListener {
            findNavController().navigate(R.id.discoverFragment)
        }
        roomDatabase()
        return binding.root
    }

    private fun roomDatabase() {
        // Initialize Room database
        val db =
            Room.databaseBuilder(requireContext(), AppDatabase::class.java, "recipe_db").build()
        recipeDao = db.recipeDao()


        /*// Initialize image picker
        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                selectedImageUri = result.data?.data

                // Hide the upload text and default image
                binding.tvUploedRecipe.visibility = View.GONE
                binding.imageViewUploedRecipe.visibility = View.GONE

                // Show the selected image
                binding.ivSelected.visibility = View.VISIBLE
                binding.ivSelected.setImageURI(selectedImageUri)
            }
        }*/

        // Open gallery to select an image
        binding.layoutUploedRecipe.setOnClickListener {
            selectedImageUri =  null
                val currentAPIVersion: Int = Build.VERSION.SDK_INT
                if (currentAPIVersion >= Build.VERSION_CODES.TIRAMISU) {
                    permissionForHigherVersion()
                } else {
                    permissionForLowerVersion()
                }
        }

        // Save recipe to Room database
        binding.tvProceed.setOnClickListener {
                val recipeName = binding.edNameOfRecipes.text.toString()
                val recipeDescription = binding.edHowToMakeRecipe.text.toString()

                if (recipeName.isNotEmpty() && recipeDescription.isNotEmpty() && selectedImageUri != null) {
                    val recipe = RecipeEntity(
                        name = recipeName,
                        description = recipeDescription,
                        imageUri = selectedImageUri.toString()
                    )
                    saveRecipeToDatabase(recipe)

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please fill all fields and select an image.",
                        Toast.LENGTH_SHORT
                    ).show()
                }



        }
    }

    /*// Convert selected image URI to byte array
    private fun convertImageToByteArray(uri: Uri): ByteArray {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        return inputStream?.readBytes() ?: byteArrayOf()
    }*/
    /*private fun convertImageToByteArray(uri: Uri): ByteArray {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)

        // Compress the bitmap to reduce size
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)  // 50% quality to reduce size
        return outputStream.toByteArray()
    }*/


    // Save recipe to Room database
    private fun saveRecipeToDatabase(recipe: RecipeEntity) {
        lifecycleScope.launch {
            recipeDao.insertRecipe(recipe)
            Toast.makeText(requireContext(), "Recipe saved successfully!", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_addRecipesFragment_to_recipesFragment)
        }
    }

    private fun permissionForLowerVersion() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            openGallery()
        } else {
            // Request permission
            requestGalleryPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun permissionForHigherVersion() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            openGallery()
        } else {
            // Request permission
            requestGalleryPermissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        val chooserIntent = Intent.createChooser(intent, "Photo")
        getImageResultLauncher.launch(chooserIntent)
    }

    private val requestGalleryPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            } else {
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

    private val getImageResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                selectedImageUri = data?.data
                if (selectedImageUri != null) {
                    try {

                        binding.apply {
                            ivSelected.visibility = View.VISIBLE
                            imageViewUploedRecipe.visibility = View.INVISIBLE
                            tvUploedRecipe.visibility = View.INVISIBLE
                            Glide.with(requireActivity()).load(selectedImageUri).into(ivSelected)
                        }

                        CoroutineScope(Dispatchers.Main).launch {
                            val recipeName = binding.edNameOfRecipes.text.toString()
                            val recipeDescription = binding.edHowToMakeRecipe.text.toString()

                            if (recipeName.isNotEmpty() && recipeDescription.isNotEmpty() && selectedImageUri != null) {
                                val recipe = RecipeEntity(
                                    name = recipeName,
                                    description = recipeDescription,
                                    imageUri = selectedImageUri.toString()
                                )
                                saveRecipeToDatabase(recipe)

                            }

                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.e("CatchError", "Error: ${e.message}")
                    }
                }
            }
        }


}