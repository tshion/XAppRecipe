package work.shion.xapprecipe.pages.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import work.shion.xapprecipe.NavEntrypointDirections
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PagesLoginBinding
import work.shion.xapprecipe_core.entities.LoginEntity
import work.shion.xapprecipe_core.errors.LoginException
import work.shion.xapprecipe_core.repositories.AuthenticateRepositoryContract
import work.shion.xapprecipe_core.usecases.CertifyAccountUseCase
import java.lang.ref.WeakReference

/**
 * ログイン
 */
class MainFragment : Fragment(), MainViewContract {

    private val args by navArgs<MainFragmentArgs>()
    private var binding: PagesLoginBinding? = null
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            certifyAccountUseCaseContract = CertifyAccountUseCase(
                authenticateRepository = object : AuthenticateRepositoryContract {
                    /**
                     * 認証済みかどうか
                     */
                    override suspend fun isAuthenticated() = false

                    /**
                     * ログイン
                     * @throws LoginException ログイン失敗
                     */
                    override suspend fun login(data: LoginEntity) {
                    }

                    /**
                     * ログアウト
                     */
                    override suspend fun logout() {
                    }
                }
            ),
            context = WeakReference(requireContext()),
            viewer = WeakReference(this),
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PagesLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.pagesLoginActionLogin?.setOnClickListener {
            viewModel.doLogin(
                id = binding?.pagesLoginId?.input,
                pw = binding?.pagesLoginPw?.input,
            )
        }
        binding?.pagesLoginActionSkip?.setOnClickListener { viewModel.doSkip() }

        if (args.canClose) {
            View.OnClickListener { activity?.onBackPressed() }
        } else {
            null
        }.also { binding?.pagesLoginHeader?.setupBackListener(it) }
    }

    override fun onStop() {
        viewModel.cancelTasks()
        super.onStop()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


    /**
     * エラー表示の反映
     */
    override fun reflectError(idError: String?, pwError: String?) {
        binding?.run {
            pagesLoginId.errors = idError
            pagesLoginPw.errors = pwError
        }
    }

    /**
     * ローディング表示状態の反映
     */
    override fun reflectLoading(shouldShow: Boolean) {
        binding?.pagesLoginLoading?.isVisible = shouldShow
    }

    /**
     * トップへ遷移する
     */
    override fun goTop() {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(NavEntrypointDirections.navactToTop())
    }
}
