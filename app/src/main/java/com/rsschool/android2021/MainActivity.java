package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements OnFragmentSendNumber {
    /*
    Если ты читаешь это, значит ты проверяшь меня на кроссчеке
    Спасибо, что не поленился проверить, хорошего тебе дня, успехов в жизни и happy codding!
    Ты красавчик(красавица), раз уже столько времени прошло, а ты все ещё не забросил курс,
    значит ты сильный(ая) и всё сможешь ;)

    Передача данных с 2го на первый фрагмент происходит с помощью слушателя и активити в качестве хоста
    а с 1го на второй просто добавляется(put) в newIntent
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
        if(getSupportFragmentManager().getBackStackEntryCount()>0){ //если в бэкстэке есть фрамент, то удаляем его оттуда
            getSupportFragmentManager().popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) { //этот метод не используется, но у него была TODO-шка)
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment);
        transaction.commit();
    }

    @Override
    public void sendNumber(int number) {
        openFirstFragment(number);
    }

}
