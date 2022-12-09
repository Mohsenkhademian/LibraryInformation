package mft.controller;

import mft.model.bl.MemberBL;
import mft.model.entity.Member;

import java.util.List;

public class MemberController {
    public static void add(String name, String family) {
        if (!name.isEmpty() && !family.isEmpty()) {
            try {
                MemberBL.add(new Member(name, family));
                System.out.println("Member Saved");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void edit(int id, String name, String family) {
        if (!name.isEmpty() && !family.isEmpty() && id > 0) {
            try {
                MemberBL.edit(new Member(id , name , family));
                System.out.println("Member Edited");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void remove(int id) {
        if (id > 0) {
            try {
                MemberBL.remove(id);
                System.out.println("Member Removed");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Member> findAll() {
        try {
            return MemberBL.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Member findById(int id) {
        try {
            return MemberBL.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
