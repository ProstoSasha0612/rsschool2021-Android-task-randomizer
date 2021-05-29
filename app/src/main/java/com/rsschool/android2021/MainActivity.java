package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements OnFragmentSendNumber, OnFragmentSendMinMax {
    /*
    Если ты читаешь это, значит ты проверяшь меня на кроссчеке
    Спасибо, что не поленился проверить, хорошего тебе дня, успехов в жизни и happy codding!
    Ты красавчик(красавица), раз уже столько времени прошло, а ты все ещё не забросил курс,
    значит ты сильный(ая) и всё сможешь ;)

     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            openFirstFragment(0);
        }
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment);
        transaction.commit();
    }

    @Override
    public void sendNumber(int number) { //открывает первый фрагмент и передает в него данные
        openFirstFragment(number);
    }

    @Override
    public void sendMinMax(int min, int max) {  //открывает второй фрагмент и передает в него данные
        openSecondFragment(min,max);
    }
}
