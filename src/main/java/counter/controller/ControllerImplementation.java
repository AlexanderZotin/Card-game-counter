package counter.controller;

import counter.view.View;
import counter.view.ViewUtils;
import counter.model.Player;
import counter.model.Counter;
import counter.exceptions.NegativeNumberException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@NonNull
@AllArgsConstructor
public class ControllerImplementation implements CounterController {
    private final @Getter View view;
    private final Counter counter;

    @Override
    public void dataCleanRequested() {
        view.cleanData();
    }

    @Override
    public void countRequested() {
        try {
            Player[] players = view.parseData();
            view.showResult(counter.count(players));
        } catch(NumberFormatException _) {
            handleNumberFormatException();
        } catch(NegativeNumberException _) {
            handleNegativNumberException();
        }
    }
    
    private void handleNumberFormatException() {
        ViewUtils.showErrorDialog("""
                <html>
                    Можно вводить только целые числа!<br>
                    Вводить нечисловые символы или десятичные дроби нельзя.<br>
                    Пожалуйста, попробуйте ещё раз.
                </html>
                """);
    }
 
    private void handleNegativNumberException() {
        ViewUtils.showErrorDialog("""
                <html>
                    В одной из полей для пули было введено отрицательное число!<br>
                    Для обозначения проигрыша игрока используется Гора.<br>
                    Пожалуйста, попробуйте ещё раз.
                </html>
                """);
    }
}