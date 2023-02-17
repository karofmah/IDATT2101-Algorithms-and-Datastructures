import java.util.Arrays;
import java.util.Scanner;

public class TaskFourPartOne {


    public static class Node {
        int element;
        Node next;
        Node previous;


        public Node(int element, Node next, Node previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }

        public int findElement() {
            return element;
        }

        public void setElement(int element) {
            this.element = element;
        }
    }


    public static class DoubleLinkedList {

        private Node getTail;
        private Node head;
        private int totalElements;


        public DoubleLinkedList(Node tail, Node head, int totalElements) {
            this.getTail = tail;
            this.head = head;
            this.totalElements = totalElements;
        }

        public Node getGetTail() {
            return getTail;
        }

        public Node getHead() {
            return head;
        }

        public void insertAtFront(int value) {
            head = new Node(value, head, null);

            if (getTail == null) {
                getTail = head;
            } else {
                head.next.previous = head;

            }
            ++totalElements;
        }

        public void insertAtBack(int value) {
            Node node = new Node(value, null, getTail);

            if (getTail != null) {
                getTail.next = node;
            } else {
                head = node;
            }
            getTail = node;
            ++totalElements;
        }

        public Node remove(Node node) {
            if (node.previous != null) {
                node.previous.next = node.next;
            } else {
                head = node.next;
            }
            if (node.next != null) {
                node.next.previous = node.previous;
            } else {
                getTail = node.previous;
            }
            node.next = null;
            node.previous = null;
            --totalElements;
            return node;
        }





        public static DoubleLinkedList sum(DoubleLinkedList listOne,DoubleLinkedList listTwo){
            DoubleLinkedList resultList = new DoubleLinkedList(null,null,0);
            Node nodeOne = listOne.getTail;
            Node nodeTwo = listTwo.getTail;
            DoubleLinkedList biggestElement;
            biggestElement = findBiggestList(listOne,listTwo);

            int memory = 0;
            int sum;
            for (int i = 0; i < biggestElement.totalElements; i++){
                int nodeOneValue = 0;
                int nodeTwoValue = 0;
                if (nodeOne != null){
                    nodeOneValue = nodeOne.element;
                }
                if (nodeTwo != null){
                    nodeTwoValue = nodeTwo.element;
                }
                sum = nodeOneValue + nodeTwoValue + memory;
                if(sum >=10){
                    memory = 1;
                }else{
                    memory = 0;
                }
                sum = sum % 10;
                resultList.insertAtFront(sum);
                if (nodeOne != null){
                    nodeOne = nodeOne.previous;
                }
                if (nodeTwo != null){
                    nodeTwo = nodeTwo.previous;
                }
            }
            if (memory > 0){
                resultList.insertAtFront(memory);
            }
            return resultList;
        }
        public static DoubleLinkedList findBiggestList(DoubleLinkedList listOne, DoubleLinkedList listTwo){
            Node onListOne = listOne.getHead();
            Node onListTwo = listTwo.getHead();
            DoubleLinkedList biggestElement = new DoubleLinkedList(null, null, 0);

            if (listOne.totalElements == listTwo.totalElements) {
                for (int i = 0; i < listOne.totalElements; i++) {
                    if (onListOne.findElement() > onListTwo.findElement()) {
                        biggestElement = listOne;
                        break;
                    } else if (onListOne.findElement() < onListTwo.findElement()) {
                        biggestElement = listTwo;
                        break;
                    }
                    else {
                        biggestElement=listOne;
                        onListOne = onListOne.next;
                        onListTwo = onListTwo.next;
                    }
                }
            }else if(listOne.totalElements>listTwo.totalElements){
                biggestElement = listOne;
            }else{
                biggestElement = listTwo;
            }
            return biggestElement;
        }


        public static DoubleLinkedList subtraction(DoubleLinkedList listOne, DoubleLinkedList listTwo) {
            DoubleLinkedList resultList = new DoubleLinkedList(null, null, 0);
            Node curListOne = listOne.getGetTail();
            Node curListTwo = listTwo.getGetTail();
            Node curResultList;


            int difference;
            int biggestSize;
            DoubleLinkedList biggestElement;


            biggestElement = findBiggestList(listOne,listTwo);


            if ((listOne.totalElements > listTwo.totalElements) || (biggestElement == listOne)) {

                difference = listOne.totalElements - listTwo.totalElements;
                for (int i = 0; i < difference; i++) {
                    listTwo.insertAtFront(0);
                }

                biggestSize = listOne.totalElements;

                for (int i = 0; i < biggestSize; i++) {
                    if (resultList.getHead() == null) {
                        resultList.insertAtFront(curListOne.findElement() - curListTwo.findElement());
                    } else {
                        curResultList = resultList.getHead();
                        if (curResultList.findElement() < 0) {
                            curResultList.setElement(curResultList.findElement() + 10);
                            curListOne = curListOne.previous;
                            curListTwo = curListTwo.previous;

                            resultList.insertAtFront(curListOne.findElement() - curListTwo.findElement());
                            curResultList = curResultList.previous;
                            curResultList.setElement(curResultList.findElement() - 1);

                        } else {
                            curListOne = curListOne.previous;
                            curListTwo = curListTwo.previous;
                            resultList.insertAtFront(curListOne.findElement() - curListTwo.findElement());
                        }
                    }
                }
            } else if ((listOne.totalElements < listTwo.totalElements) || (biggestElement == listTwo)) {
                difference = listTwo.totalElements - listOne.totalElements;
                for (int i = 0; i < difference; i++) {
                    listOne.insertAtFront(0);
                }

                biggestSize = listTwo.totalElements;


                for (int i = 0; i < biggestSize; i++) {
                    if (resultList.getHead() == null) {
                        resultList.insertAtFront(curListTwo.findElement() - curListOne.findElement());
                    } else {
                        curResultList = resultList.getHead();
                        if (curResultList.findElement() < 0) {
                            curResultList.setElement(curResultList.findElement() + 10);
                            curListOne = curListOne.previous;
                            curListTwo = curListTwo.previous;

                            resultList.insertAtFront(curListTwo.findElement() - curListOne.findElement());
                            curResultList = curResultList.previous;
                            curResultList.setElement(curResultList.findElement() - 1);
                            resultList.getHead().setElement((resultList.getHead().findElement()));
                        } else {
                            curListOne = curListOne.previous;
                            curListTwo = curListTwo.previous;
                            resultList.insertAtFront(curListTwo.findElement() - curListOne.findElement());
                        }
                    }
                }
                resultList.getHead().setElement((resultList.getHead().findElement()));
            }

            boolean notDone = true;
            Node onResultList = resultList.getHead();
            if (onResultList.findElement() == 0) {
                while (notDone) {
                    for (int i = 0; i < resultList.totalElements + 1; i++) {
                        if (onResultList.findElement() == 0 && resultList.totalElements != 1) {
                            resultList.remove(resultList.getHead());
                            onResultList = resultList.getHead();
                        } else {
                            notDone = false;
                        }
                    }
                }
            }
            return resultList;
        }

        public String display() {
            Node current = head;
            String emptyString = "";
            if (head == null) {
                System.out.print("List is empty");
            } else {
                while (current != null) {
                    System.out.print(current.element + "");
                    current = current.next;
                }
            }
            return emptyString;
        }

        public static DoubleLinkedList createListFromString(String input) {
            DoubleLinkedList list = new DoubleLinkedList(null, null, 0);
            Arrays.stream(input.split("")).forEach(s -> list.insertAtBack(Integer.parseInt(s)));
            return list;
        }



        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("This program does not support input of negative numbers!\n");
            System.out.println("Calculations like: \n (-)number - (-)number \n Does not work \n Please only input int numbers > 0");
            while (true) {
                System.out.println("\nWrite first number");
                String valueOne = sc.next();
                System.out.println("What operation do you want, '+' or '-' ");
                String operation = sc.next();
                System.out.println("Write second number");
                String valueTwo = sc.next();
                if (operation.equals("+")) {
                    System.out.println(valueOne + " + " + valueTwo + " =\n");
                    System.out.println(sum(createListFromString(valueOne), createListFromString(valueTwo)).display());
                } else if (operation.equals("-")) {

                    DoubleLinkedList listOne = createListFromString(valueOne);
                    DoubleLinkedList listTwo = createListFromString(valueTwo);
                    DoubleLinkedList biggestElement;
                    biggestElement = findBiggestList(listOne,listTwo);

                    if (biggestElement == listTwo) {
                        System.out.println(valueOne + " - " + valueTwo + " =\n");
                        System.out.print("-");
                        System.out.print(subtraction(listOne, listTwo).display());
                    } else if (biggestElement == listOne) {
                        System.out.println(valueOne + " - " + valueTwo + " =\n");
                        System.out.print(subtraction(listOne, listTwo).display());
                    } else if (listTwo.totalElements > listOne.totalElements) {
                        System.out.println(valueOne + " - " + valueTwo + " =\n");
                        System.out.print("-");
                        System.out.print(subtraction(listOne, listTwo).display());
                    } else {
                        System.out.println(valueOne + " - " + valueTwo + " =\n");
                        System.out.print(subtraction(listOne, listTwo).display());
                    }
                }
            }
        }
    }
}
