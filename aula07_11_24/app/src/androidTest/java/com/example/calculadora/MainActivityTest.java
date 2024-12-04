package com.example.calculadora;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.projeto_calculadora.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

// Define que o código será executado com o AndroidJUnit4
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    // Regra para lançar a Activity MainActivity antes de executar os testes
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // Teste para verificar o conteúdo inicial dos TextViews
    @Test
    public void testTextViewContent() {
        // Verifica o estado inicial do TextView de histórico (deve estar vazio no início)
        onView(withId(R.id.historico)).check(matches(withText("")));

        // Verifica o estado inicial do TextView de conta (deve estar vazio no início)
        onView(withId(R.id.conta)).check(matches(withText("0")));
    }

    // Teste para simular pressionamento de botões e verificar o input após as interações
    @Test
    public void testInputAfterButtonPress() {
        // Simula pressionamento de botões e verifica o TextView de entrada (histórico)
        onView(withId(R.id.btn8)).perform(click()); // Pressiona "8"
        onView(withId(R.id.btnAdicao)).perform(click()); // Pressiona "+"
        onView(withId(R.id.btn3)).perform(click()); // Pressiona "3"

        // Verifica se o TextView de histórico mostra "8.0 +"
        onView(withId(R.id.historico)).check(matches(withText("8.0 +")));
        // Verifica se o TextView de conta mostra "3"
        onView(withId(R.id.conta)).check(matches(withText("3")));
    }

    // Teste para verificar a saída após um cálculo (soma)
    @Test
    public void testOutputAfterCalculation() {
        // Simula o pressionamento dos botões para realizar a soma
        onView(withId(R.id.btn8)).perform(click()); // Pressiona "8"
        onView(withId(R.id.btnAdicao)).perform(click()); // Pressiona "+"
        onView(withId(R.id.btn3)).perform(click()); // Pressiona "3"
        onView(withId(R.id.btnIgual)).perform(click()); // Pressiona "=" para calcular

        // Verifica se o resultado no TextView de conta é "11"
        onView(withId(R.id.conta)).check(matches(withText("11.0")));
        onView(withId(R.id.historico)).check(matches(withText("8.0 + 3.0")));
    }

    // Teste para verificar a saída após um cálculo de subtração
    @Test
    public void testSubCalculation(){
        // Simula o pressionamento dos botões para realizar a subtração
        onView(withId(R.id.btn8)).perform(click());
        onView(withId(R.id.btnSubitracao)).perform(click());
        onView(withId(R.id.btn3)).perform(click());
        onView(withId(R.id.btnIgual)).perform(click());

        // Verifica se o resultado no TextView de conta é "5.0"
        onView(withId(R.id.conta)).check(matches(withText("5.0")));
        onView(withId(R.id.historico)).check(matches(withText("8.0 - 3.0")));
    }

    // Teste para verificar a saída após um cálculo de multiplicação
    @Test
    public void testMultCalculation(){
        // Simula o pressionamento dos botões para realizar a multiplicação
        onView(withId(R.id.btn8)).perform(click());
        onView(withId(R.id.btnMultiplicacao)).perform(click());
        onView(withId(R.id.btn3)).perform(click());
        onView(withId(R.id.btnIgual)).perform(click());

        // Verifica se o resultado no TextView de conta é "24.0"
        onView(withId(R.id.conta)).check(matches(withText("24.0")));
        onView(withId(R.id.historico)).check(matches(withText("8.0 * 3.0")));
    }

    // Teste para verificar a saída após um cálculo de divisão
    @Test
    public void testDivCalculation(){
        // Simula o pressionamento dos botões para realizar a divisão
        onView(withId(R.id.btn8)).perform(click());
        onView(withId(R.id.btnDivisao)).perform(click());
        onView(withId(R.id.btn3)).perform(click());
        onView(withId(R.id.btnIgual)).perform(click());

        // Verifica se o resultado no TextView de conta é "2.6666667"
        onView(withId(R.id.conta)).check(matches(withText("2.6666667")));
        onView(withId(R.id.historico)).check(matches(withText("8.0 / 3.0")));
    }

    // Teste para verificar a saída ao realizar uma operação de porcentagem
    @Test
    public void testPorcentagemCalculation(){
        // Simula o pressionamento dos botões para realizar a porcentagem
        onView(withId(R.id.btn8)).perform(click());
        onView(withId(R.id.btnPorcentagem)).perform(click());

        // Verifica se o resultado no TextView de conta é "0.08"
        onView(withId(R.id.conta)).check(matches(withText("0.08")));
        onView(withId(R.id.historico)).check(matches(withText("")));
    }

    // Teste para verificar a inversão de sinal
    @Test
    public void testInversaoCalculation(){
        // Simula o pressionamento dos botões para inverter o sinal
        onView(withId(R.id.btn8)).perform(click());
        onView(withId(R.id.btnInverterSinal)).perform(click());

        // Verifica se o resultado no TextView de conta é "-8.0"
        onView(withId(R.id.conta)).check(matches(withText("-8.0")));
        onView(withId(R.id.historico)).check(matches(withText("")));
    }

    // Teste para verificar a limpeza dos campos
    @Test
    public void testLimparCalculation(){
        // Simula o pressionamento dos botões para limpar os campos
        onView(withId(R.id.btn8)).perform(click());
        onView(withId(R.id.btnInverterSinal)).perform(click());
        onView(withId(R.id.btnAC)).perform(click());

        // Verifica se o TextView de conta foi resetado para "0"
        onView(withId(R.id.conta)).check(matches(withText("0")));
        onView(withId(R.id.historico)).check(matches(withText("")));
    }

    // Teste para verificar o histórico de cálculos realizados
    @Test
    public void testHistoricoCalculation(){
        // Simula a realização de uma soma e verifica o histórico
        onView(withId(R.id.btn8)).perform(click()); // Pressiona "8"
        onView(withId(R.id.btnAdicao)).perform(click()); // Pressiona "+"
        onView(withId(R.id.btn3)).perform(click()); // Pressiona "3"
        onView(withId(R.id.btnIgual)).perform(click()); // Pressiona "=" para calcular

        // Verifica se o resultado no TextView de conta é "11"
        onView(withId(R.id.conta)).check(matches(withText("11.0")));
        onView(withId(R.id.historico)).check(matches(withText("8.0 + 3.0")));

        // Realiza uma subtração e verifica o resultado
        onView(withId(R.id.btnSubitracao)).perform(click());
        onView(withId(R.id.btn3)).perform(click());
        onView(withId(R.id.btnIgual)).perform(click());

        // Verifica se o resultado no TextView de conta é "8.0"
        onView(withId(R.id.conta)).check(matches(withText("8.0")));
        onView(withId(R.id.historico)).check(matches(withText("11.0 - 3.0")));
    }

    @Test
    public void testDecimalCalculation(){
        onView(withId(R.id.btn8)).perform(click());
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btn3)).perform(click());

        // Verifica se o TextView de conta foi resetado para "8.3"
        onView(withId(R.id.conta)).check(matches(withText("8.3")));
        onView(withId(R.id.historico)).check(matches(withText("")));
    }

    @Test
    public void testERROR1Calculation(){
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btnIgual)).perform(click());

        // Verifica se o TextView de conta foi resetado para "0"
        onView(withId(R.id.conta)).check(matches(withText("0.0")));
        onView(withId(R.id.historico)).check(matches(withText("0.0")));
    }

    @Test
    public void testERROR2Calculation(){
        onView(withId(R.id.btnIgual)).perform(click());

        // Verifica se o TextView de conta foi resetado para "0"
        onView(withId(R.id.conta)).check(matches(withText("0.0")));
        onView(withId(R.id.historico)).check(matches(withText("0.0")));
    }

    @Test
    public void testERROR3Calculation(){
        onView(withId(R.id.btn8)).perform(click());
        onView(withId(R.id.btnDivisao)).perform(click());
        onView(withId(R.id.btn0)).perform(click());
        onView(withId(R.id.btnIgual)).perform(click());

        // Verifica se o TextView de conta foi resetado para "Erro: Div/0"
        onView(withId(R.id.conta)).check(matches(withText("Erro: Div/0")));
        onView(withId(R.id.historico)).check(matches(withText("8.0 / 0.0")));
    }

    @Test
    public void testERROR4Calculation(){
        onView(withId(R.id.btn8)).perform(click());
        onView(withId(R.id.btnDivisao)).perform(click());
        onView(withId(R.id.btnMultiplicacao)).perform(click());
        onView(withId(R.id.btnSubitracao)).perform(click());
        onView(withId(R.id.btn0)).perform(click());
        onView(withId(R.id.btnIgual)).perform(click());

        onView(withId(R.id.conta)).check(matches(withText("8.0")));
        onView(withId(R.id.historico)).check(matches(withText("8.0 - 0.0")));
    }

    @Test
    public void testERROR5Calculation(){
        onView(withId(R.id.btn8)).perform(click());
        onView(withId(R.id.btnDivisao)).perform(click());
        onView(withId(R.id.btnMultiplicacao)).perform(click());
        onView(withId(R.id.btnSubitracao)).perform(click());
        onView(withId(R.id.btn1)).perform(click());
        onView(withId(R.id.btn6)).perform(click());
        onView(withId(R.id.btnIgual)).perform(click());

        onView(withId(R.id.conta)).check(matches(withText("-8.0")));

        onView(withId(R.id.btnInverterSinal)).perform(click());
        onView(withId(R.id.conta)).check(matches(withText("8.0")));

        onView(withId(R.id.btnInverterSinal)).perform(click());
        onView(withId(R.id.conta)).check(matches(withText("-8.0")));

        onView(withId(R.id.btnDivisao)).perform(click());
        onView(withId(R.id.btnMultiplicacao)).perform(click());
        onView(withId(R.id.btn3)).perform(click());
        onView(withId(R.id.btnInverterSinal)).perform(click());
        onView(withId(R.id.btnIgual)).perform(click());

        onView(withId(R.id.conta)).check(matches(withText("24.0")));
        onView(withId(R.id.btnIgual)).perform(click());

        onView(withId(R.id.conta)).check(matches(withText("-192.0")));
        onView(withId(R.id.historico)).check(matches(withText("-8.0 * 24.0")));
        onView(withId(R.id.btnIgual)).perform(click());

        onView(withId(R.id.conta)).check(matches(withText("1536.0")));
        onView(withId(R.id.historico)).check(matches(withText("-8.0 * -192.0")));
    }

}
