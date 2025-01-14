import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static int role, action;

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        clearScreen();
        ArrayList<Course> courseList = new ArrayList<>();
        ArrayList<Course> registeredCourseList = new ArrayList<>();
        ArrayList<Student> enrolledStduentList = new ArrayList<>();
        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();

        // StudentController sController = new
        // StudentController(studentList,courseList);
        LecturerController lController = new LecturerController(courseList, studentList);
        // AdminController aController = new AdminController(admins);

        Lecturer lecturer1 = new Lecturer("Ali", "123");
        Lecturer lecturer2 = new Lecturer("Abu", "456");
        lecturers.add(lecturer1);
        lecturers.add(lecturer2);

        AdminView adminView = new AdminView();
        StudentView studentView = new StudentView();
        CourseView courseView= new CourseView();
        LecturerView lectureView= new LecturerView();
        AdminController aController = new AdminController(courseList);//student list
        StudentController studentController= new StudentController();


        Scanner s = new Scanner(System.in);
        do {
            System.out.println("| Course Registration System |");
            System.out.println("1. Admin \n2. Lecturer \n3. Student ");
            System.out.print("Enter your role >> ");
            role = s.nextInt();
            s.nextLine();
            switch (role) {
                case 1: {
                    clearScreen();
                    System.out.println("Howdy Admin!");
                    System.out.println("1. Display course \n2. Add courses \n3. Remove course \n4. View Student");
                    System.out.print("Enter your action >> ");
                    action = s.nextInt();
                    switch (action) {
                        case 1: {
                            clearScreen();

                            // Display course
                            adminView.displayCourseDetails(courseList);

                            break;
                        }
                        case 2: {
                            clearScreen();

                            // Add courses
                            aController.addCourse();

                            break;
                        }
                        case 3: {
                            clearScreen();
                            // Remove courses
                            aController.deleteCourse();
                            break;
                        }
                        case 4: {
                            clearScreen();
                            // View student
                            courseView.displayStudentRegistered(enrolledStduentList);
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    clearScreen();
                    System.out.println("Howdy Lecturer!");
                    Boolean foundLec = false;
                    // verify identity
                    System.out.print("Enter your name >> ");
                    String lecturerName = s.nextLine();
                    for (int i = 0; i < lecturers.size(); i++) {
                        if (lecturerName.equals(lecturers.get(i).getName())) {
                            foundLec = true;
                        }
                    }
                    if (foundLec == true) {
                        System.out.println("1. View registered course \n2. Register Roster \n3. View Student List");
                        System.out.print("Enter your action >> ");
                        action = s.nextInt();
                        s.nextLine();
                        switch (action) {
                            case 1: {
                                clearScreen();
                                // View course logic
                                lController.viewRegisteredCourse(lecturerName);
                                break;
                            }
                            case 2: {
                                clearScreen();
                                // Register Roster logic
                                lController.registerRoster(lecturerName);
                                break;
                            }
                            case 3: {
                                clearScreen();
                                // View student list
                                // Choose from registered roster
                                lectureView.displayStudentList(enrolledStduentList);
                                break;
                            }
                        }
                    } else {
                        System.out.println("Login as Lecturer Failed");
                    }
                    break;
                }
                case 3: {
                    clearScreen();
                    System.out.println("Howdy Student!");
                    //verify identity

                    System.out.println("\n1. View Courses \n2. Register for course \n3. View Registered Courses");
                    System.out.print("Enter your action >> ");
                    action = s.nextInt();
                    s.nextLine();
                    switch (action) {
                        case 1: {
                            clearScreen();

                            // View courses
                            studentView.displayCourseDetails(courseList);
                            break;
                        }
                        case 2: {
                            clearScreen();

                            // Register for course
                            studentController.registerCourse(courseList, registeredCourseList, enrolledStduentList);

                            break;
                        }
                        case 3: {
                            clearScreen();

                            // View registered courses
                            studentView.displayRegisteredCourse(registeredCourseList);

                            break;
                        }
                    }
                    break;
                }
                default: {
                    clearScreen();
                    System.out.println("Bye!");
                    break;
                }
            }
        } while (role == 1 || role == 2 || role == 3);

    }
}