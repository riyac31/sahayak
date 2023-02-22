package com.byteridge.sahayak.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="patientData")
@Builder
public class Patient implements List<Patient> {
    String idProof;
    String user_id;

    String disease;
    String phoneNo;

    String password;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Patient> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Patient patient) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Patient> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Patient> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Patient get(int index) {
        return null;
    }

    @Override
    public Patient set(int index, Patient element) {
        return null;
    }

    @Override
    public void add(int index, Patient element) {

    }

    @Override
    public Patient remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Patient> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Patient> listIterator(int index) {
        return null;
    }

    @Override
    public List<Patient> subList(int fromIndex, int toIndex) {
        return null;
    }
}
