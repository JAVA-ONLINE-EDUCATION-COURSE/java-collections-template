# Шаблон для практической работы по теме Java Collections

Дан фрагмент текста по адресу `src/main/resources/sample-text.txt`. Вам необходимо реализовать 2 сервиса:
* `SimpleTextStatisticsAnalyzer`
* `StreamApiTextStatisticsAnalyzer`

Они имплементируют интерфейс `TextStatisticsAnalyzer`, в котором объявлены несколько методов, которые используют текст в
качестве параметра, например:
* Просуммировать число слов в тексте
* Получить множество слов из текста
* Получить статистику повторений слов в тесте  
и так далее

В процессе выполнения нужно использовать классы из `Java Collections Api`. В сервисе `SimpleTextStatisticsAnalyzer` методы
должны быть имплементированы с использованием циклов, а в сервисе `StreamApiTextStatisticsAnalyzer` с использованием 
`Stream API`
