/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

/**
 *
 * @author vanes
 */
public class PrincipalJF extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalJF
     */
    public PrincipalJF() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        areaTrabalho = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        mCadastro = new javax.swing.JMenu();
        miEmpresa = new javax.swing.JMenuItem();
        miDepartamento = new javax.swing.JMenuItem();
        mAjuda = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(getMaximumSize());

        javax.swing.GroupLayout areaTrabalhoLayout = new javax.swing.GroupLayout(areaTrabalho);
        areaTrabalho.setLayout(areaTrabalhoLayout);
        areaTrabalhoLayout.setHorizontalGroup(
            areaTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );
        areaTrabalhoLayout.setVerticalGroup(
            areaTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        mCadastro.setText("Cadastro");

        miEmpresa.setText("Empresa");
        miEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEmpresaActionPerformed(evt);
            }
        });
        mCadastro.add(miEmpresa);

        miDepartamento.setText("Departamento");
        miDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDepartamentoActionPerformed(evt);
            }
        });
        mCadastro.add(miDepartamento);

        jMenuBar1.add(mCadastro);

        mAjuda.setText("Ajuda");
        jMenuBar1.add(mAjuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(areaTrabalho)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(areaTrabalho)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEmpresaActionPerformed
//        EmpresaJF tela = new EmpresaJF();
//        tela.setVisible(true);
        (new EmpresaJF()).setVisible(true);
    }//GEN-LAST:event_miEmpresaActionPerformed

    private void miDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDepartamentoActionPerformed
        DepartamentoIF tela = new DepartamentoIF();
        areaTrabalho.add(tela);
        tela.setVisible(true);
    }//GEN-LAST:event_miDepartamentoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalJF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane areaTrabalho;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu mAjuda;
    private javax.swing.JMenu mCadastro;
    private javax.swing.JMenuItem miDepartamento;
    private javax.swing.JMenuItem miEmpresa;
    // End of variables declaration//GEN-END:variables
}