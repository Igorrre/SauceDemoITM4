package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
@Log4j2
public class Retry implements IRetryAnalyzer {

    private static final int MAX_RETRY = 3;
    private int attempt = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {//Проверяем, если тест не пройден успешно
            if (attempt < MAX_RETRY) {//Проверяем, достигнуто ли максимальное количество попыток
                attempt++;// Увеличиваем maxTry count на 1
                iTestResult.setStatus(ITestResult.FAILURE); //Отметить тест как неудавшийся
                log.warn("Retrying once again");
                return true;//Сообщает TestNG о необходимости повторного запуска теста
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);//Если maxCount достигнут, тест помечается как проваленный
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);//Если тест пройден, TestNG отмечает его как пройденный
        }
        return false;
    }
}