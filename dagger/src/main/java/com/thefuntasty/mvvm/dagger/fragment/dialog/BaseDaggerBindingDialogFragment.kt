package com.thefuntasty.mvvm.dagger.fragment.dialog

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.thefuntasty.mvvm.BaseViewModel
import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.fragment.dialog.BindingViewModelDialogFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseDaggerBindingDialogFragment<VM : BaseViewModel<VS>, VS : ViewState, B : ViewDataBinding> :
    BindingViewModelDialogFragment<VM, VS, B>(), HasSupportFragmentInjector {

    @Inject internal lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector
}

