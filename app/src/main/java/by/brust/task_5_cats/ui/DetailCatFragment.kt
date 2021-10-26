package by.brust.task_5_cats.ui

import android.annotation.SuppressLint
import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.brust.task_5_cats.R
import by.brust.task_5_cats.databinding.DetailCatFragmentBinding
import by.brust.task_5_cats.utils.Constants
import coil.load
import java.io.File
import java.io.OutputStream
import java.lang.Exception
import java.util.Objects

class DetailCatFragment : Fragment() {

    private var binding: DetailCatFragmentBinding? = null
    private val viewModel by viewModels<DetailCatViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DetailCatFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            it.detailCatImage.load(viewModel.image?.url) {
                allowHardware(false)
                placeholder(R.drawable.ic_baseline_scatter_plot_24)
            }
            it.detailCatSaveFab.setOnClickListener {
                val bitmap = binding?.detailCatImage?.drawToBitmap()
                saveImageToStorage(bitmap)
            }
            it.detailCatBackFab.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun saveImageToStorage(bitmap: Bitmap?) {
        var ofs: OutputStream
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val resolver = context?.contentResolver
                val contentValues = ContentValues()
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "image_" + ".jpg")
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + File.separator + "TestFolder")
                val imageUri = resolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues)
                ofs = Objects.requireNonNull(imageUri)?.let { resolver?.openOutputStream(it) }!!
                bitmap?.compress(Bitmap.CompressFormat.JPEG, Constants.QUALITY, ofs)
                Objects.requireNonNull<OutputStream>(ofs)
                Toast.makeText(requireContext(), "Image Saved", Toast.LENGTH_SHORT).show()
            }
        } catch (exception: Exception) {
            Toast.makeText(requireContext(), "Image Not Saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
