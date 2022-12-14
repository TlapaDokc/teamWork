import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] products = {"Хлеб", "Творог", "Кефир", "Колбаса", "Пельмени"};
        double[] prices = {67.90, 76.60, 45.50, 356.20, 220.80};
        String[] productsSale = {"Кефир", "Колбаса"};
        DecimalFormat dF = new DecimalFormat("0.00");
        boolean sale = false;
        System.out.println("Список товара: ");

        int[] prod = new int[products.length];
        int cnt = 0;
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + " " + products[i] + " " +
                    dF.format(prices[i]) + " руб/шт.");
        }

        System.out.println("Товар по акции 3 по цене 2 ");
        for (int i = 0; i < productsSale.length; i++) {
            System.out.println(productsSale[i]);
        }

        while (true) {
            System.out.println("Введите номер товара и количество через пробел.");
            System.out.println("Для завершения введите end ");
            String input = scan.nextLine();

            if (input.equals("end") && cnt == 0) {
                System.out.println("Корзина: пуста.");
                System.out.println("Программа завершена.");
                break;
            } else if (input.equals("end")) {
                System.out.print("Корзина: ");
                double sum = 0;
                double sumSale;
                for (int i = 0; i < prod.length; i++) {
                    if (prod[i] != 0) {
                        for (int l = 0; l < productsSale.length; l++) {
                            if (products[i].equals(productsSale[l])) {
                                sale = true;
                                sumSale = ((prod[i] - prod[i] % 3) * prices[i] * 2) / 3 + prod[i] % 3 * prices[i];
                                System.out.println("Товар по акции 3 по цене 2 ");
                                System.out.println(products[i] + " " + prod[i] + " шт.   " +
                                        dF.format(prices[i]) + " руб/шт. " + " цена по акции " +
                                        dF.format(sumSale) + " руб.");
                                sum += sumSale;
                            }
                        }
                        if (!sale) {
                            System.out.println(products[i] + " " + prod[i] + " шт.   " +
                                    dF.format(prices[i]) + " руб/шт. " + " цена за всё " +
                                    dF.format(prod[i] * prices[i]) + " руб.");
                            sum += prod[i] * prices[i];
                        }
                    }
                }
                if (sum == 0) {
                    System.out.println("пуста.");
                } else {
                    System.out.println("Итог: " + dF.format(sum) + " руб.");
                }
                System.out.println("Программа завершена.");
                break;
            }
            String[] inAmount = input.split(" ");

            if (inAmount.length != 2) {
                System.out.println("НЕ ВЕРНО Вы ввели " + input);
                continue;
            }

            try {
                int productNumb = (Integer.parseInt(inAmount[0])) - 1;

                if ((productNumb + 1) > products.length || (productNumb + 1) <= 0) {
                    System.out.println("Не верный номер продукта. Вы ввели " + (productNumb + 1));
                    System.out.println("Введите номер от 1 до" + " " + products.length);
                    continue;
                }

                int amount = (Integer.parseInt(inAmount[1]));

                if (amount < 0) {
                    prod[productNumb] += amount;
                    if (prod[productNumb] >= 0) {
                        continue;
                    } else {
                        prod[productNumb] -= amount;
                        System.out.println("НЕ ВЕРНО. Количество не может быть меньше 1 шт. ");
                        System.out.println("Вы ввели " + amount);
                        continue;
                    }
                }

                if (amount == 0) {
                    prod[productNumb] = 0;
                    continue;
                }

                prod[productNumb] += amount;

            } catch (NumberFormatException e) {
                System.out.println("НЕ ВЕРНО. Необходимо вводить только числа.");
                System.out.println("Вы ввели " + input);
                continue;
            }
            cnt++;
        }
    }
}

