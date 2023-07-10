package ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.lachula.MainActivity
import com.example.lachula.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //cargar animacion
        val animation = AnimationUtils.loadAnimation(this,R.anim.animation)
        //asignarlo a un componente
        ivSplashScreen.startAnimation(animation)
        //variable que permita cambiar desde el origen (this a mainActivity)
        val intent=  Intent(this,MainActivity::class.java)

        //eventos luego de terminar la animacion
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                //luego de terminar que cambie de activity
                //inicializa la actividad
                startActivity(intent)
                //al dar atras,cerrarla o cuando termine la animacion del splashscreen se llama a la funcion finish para destruirla
                finish()

            }
            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
    }
}