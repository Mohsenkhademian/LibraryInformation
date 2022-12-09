package mft.model.bl;

import mft.model.entity.Member;
import mft.model.repository.MemberDA;

import java.util.List;

public class MemberBL {

    public static void add(Member member) throws Exception{
        try(MemberDA memberDA = new MemberDA()){
            memberDA.add(member);
        }
    }
    public static void edit(Member member) throws Exception{
        try(MemberDA memberDA = new MemberDA()){
            memberDA.edit(member);
        }
    }
    public static void remove(int id) throws Exception{
        try(MemberDA memberDA = new MemberDA()){
            memberDA.remove(id);
        }
    }
    public static List<Member> findAll() throws Exception{
        try(MemberDA memberDA = new MemberDA()){
           return memberDA.findAll();
        }
    }
    public static Member findById(int id) throws Exception{
        try(MemberDA memberDA = new MemberDA()){
           return memberDA.findById(id);
        }
    }
}
