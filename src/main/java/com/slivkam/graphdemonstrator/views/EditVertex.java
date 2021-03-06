package com.slivkam.graphdemonstrator.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.slivkam.graphdemonstrator.model.VertexAdapter;
import com.slivkam.graphdemonstrator.presenters.EditVertexPresenter;
import com.slivkam.graphdemonstrator.presenters.EditVertexPresenter.VertexEditor;
import com.slivkam.graphdemonstrator.presenters.Presenter.View;

public class EditVertex extends JDialog implements VertexEditor, View {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtfldID;
	private JTextField txtfldAtrName;
	private JTextField txtfldAtrValue;
	private JList<String> listAttr;
	
	private EditVertexPresenter presenter;

	/**
	 * Create the dialog.
	 */
	public EditVertex(EditVertexPresenter p) {
		super();
		presenter = p;
		initComponents();
	}

	private void initComponents() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblId = new JLabel("ID:");
		
		txtfldID = new JTextField();
		txtfldID.setEditable(false);
		txtfldID.setColumns(10);
		
		JLabel lblAttributes = new JLabel("Attributes:");
		
		listAttr = new JList<String>();
		listAttr.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				presenter.addAttribute(txtfldAtrName.getText(),txtfldAtrValue.getText());
				txtfldAtrName.setText("");
				txtfldAtrValue.setText("");
			}
			
		});
		
		JLabel lblName = new JLabel("Name:");
		
		txtfldAtrName = new JTextField();
		txtfldAtrName.setColumns(10);
		
		JLabel lblValue = new JLabel("Value:");
		
		txtfldAtrValue = new JTextField();
		txtfldAtrValue.setColumns(10);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				presenter.removeAttribute(listAttr.getSelectedValue());
				
			}
			
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAttributes)
						.addComponent(lblId))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(listAttr, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemove))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtfldAtrName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblName))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblValue, Alignment.LEADING)
								.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
									.addComponent(txtfldAtrValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAdd))))
						.addComponent(txtfldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(txtfldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAttributes)
						.addComponent(listAttr, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRemove))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValue)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtfldAtrName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtfldAtrValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd))
					.addGap(33))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						presenter.saveChanges();
						EditVertex.this.dispose();
						
					}
					
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						EditVertex.this.dispose();
						
					}
					
				});
				buttonPane.add(cancelButton);
			}
		}
	}

	public void showVertexProp(VertexAdapter v) {
		txtfldID.setText(v.getId());
		DefaultListModel<String> m = new DefaultListModel<String>();
		for(String key : v.getAttributes().keySet()) {
			m.addElement(key + " : " + v.getAttribute(key));
		}
		listAttr.setModel(m);
		listAttr.repaint();
	}

	public void addToList(String s) {
		DefaultListModel<String> m = (DefaultListModel<String>) listAttr.getModel();
		m.addElement(s);
		listAttr.setModel(m);
		listAttr.repaint();
	}

	public void removeFromList(String s) {
		DefaultListModel<String> m = (DefaultListModel<String>) listAttr.getModel();
		m.removeElement(s);
		listAttr.setModel(m);
		listAttr.repaint();
		
	}
}
