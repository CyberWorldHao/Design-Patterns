/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab10.IteratorSearchManager;

/**
 *
 * @author CWH
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Candidate {

    private String name, currWorkLocation, certType;

    public Candidate(String name, String certType, String currWorkLocation) {
        this.name = name;
        this.certType = certType;
        this.currWorkLocation = currWorkLocation;
    }

    public String getName() {
        return name;
    }

    public String getCertType() {
        return certType;
    }

    public String getCurrWorKLocation() {
        return currWorkLocation;
    }

}

class CertifiedCandidatesIterator implements Iterator {

    ArrayList<Candidate> allCandidatesList;
    String typeToBeSearched;
    int position = 0;

    public CertifiedCandidatesIterator(String type, ArrayList<Candidate> data) {
        allCandidatesList = data;
        typeToBeSearched = type;

    }

    @Override
    public Candidate next() {
        Candidate candidate = (Candidate) allCandidatesList.get(position);
        position = position + 1;
        return candidate.getCertType().equalsIgnoreCase(typeToBeSearched) ? candidate : null;
    }

    @Override
    public boolean hasNext() {
        return !(position >= allCandidatesList.size() || allCandidatesList.get(position) == null);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("The scope of the application does not deal with deleting "
                + "the profile of a candidate");
    }
}

class AllCandidates {

    ArrayList<Candidate> data = new ArrayList<>();
    File myObj = new File("Candidates.txt");

    public AllCandidates() {
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] attributes = line.split(",");
                addCandidate(attributes[0], attributes[1], attributes[2]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    private void addCandidate(String name, String certType, String currWorkLocation) {
        data.add(new Candidate(name, certType, currWorkLocation));
    }

    public Iterator<Candidate> getAllCandidatesIterator() {
        return data.iterator();
    }

    public Iterator<Candidate> getCertifiedCandidatesIterator(String type) {
        return new CertifiedCandidatesIterator(type, data);
    }

    @Override
    public String toString() {
        String tempStr = "";
        System.out.println("Printing file content: ");
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                tempStr += myReader.nextLine() + "\n";
            }
            tempStr = tempStr.substring(0, tempStr.length() - 1);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        return tempStr;
    }

}

public class IteratorSearchManager {

    public static void main(String[] args) {
        AllCandidates allCandidates = new AllCandidates();
        System.out.println(allCandidates.toString());
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        int choiceNum = -1;

        OUTER:
        while (choiceNum != 0) {
            System.out.print("Enter 1 to display all candidates, 2 to display certified candidates, 0 to exit: ");
            try {
                choiceNum = Integer.parseInt(scanner.nextLine());
                switch (choiceNum) {
                    case 0:
                        break OUTER;
                    case 1:
                        System.out.println(allCandidates.toString());
                        break;
                    case 2:
                        System.out.print("Enter certification type: ");
                        Iterator iterator = allCandidates.getCertifiedCandidatesIterator(scanner.nextLine().trim());
                        boolean NoSuchElementException = true;
                        while (iterator.hasNext()) {
                            Candidate candidate = (Candidate)iterator.next();
                            if (candidate != null) {
                                System.out.print(candidate.getName() + ", ");
                                System.out.print(candidate.getCertType() + ", ");
                                System.out.println(candidate.getCurrWorKLocation());
                                NoSuchElementException = false;
                            }
                        }
                        if (NoSuchElementException) {
                            throw new NoSuchElementException("There is none candidate has the specified certification type");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
